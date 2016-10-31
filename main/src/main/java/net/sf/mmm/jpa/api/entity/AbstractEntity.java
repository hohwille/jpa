/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.jpa.api.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.id.GenericId;
import net.sf.mmm.util.data.api.id.Id;

/**
 * Base implementation of a JPA {@link Entity}.
 *
 * @see javax.persistence.Entity
 *
 * @author hohwille
 */
@MappedSuperclass
public class AbstractEntity implements Entity {

  private transient Id<?> id;

  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Long primaryKey;

  @Version
  private long version;

  /**
   * @return the primaryKey
   */
  public Long getPrimaryKey() {

    return this.primaryKey;
  }

  /**
   * @param primaryKey is the primaryKey to set
   */
  public void setPrimaryKey(Long primaryKey) {

    if (!Objects.equals(this.primaryKey, primaryKey)) {
      this.primaryKey = primaryKey;
      this.id = null;
    }
  }

  /**
   * @return the {@link Id#getVersion() modification counter}.
   */
  public long getVersion() {

    return this.version;
  }

  /**
   * @param version the new {@link #getVersion() version}.
   */
  public void setVersion(long version) {

    if (this.version != version) {
      this.version = version;
      this.id = null;
    }
  }

  @Override
  public Id<?> getId() {

    if ((this.id == null) && (this.primaryKey != null)) {
      this.id = GenericId.of(getEntityClass(), this.primaryKey.longValue(), this.version);
    }
    return this.id;
  }

  private Class<? extends AbstractEntity> getEntityClass() {

    Class<? extends AbstractEntity> entityClass = getClass();
    // entityClass = Hibernate.getClass(entityClass);
    return entityClass;
  }

  @Override
  public void setId(Id<?> id) {

    Id<?> newId = id;
    if (newId == null) {
      this.primaryKey = null;
      this.version = 0;
    } else {
      Class<?> type = newId.getType();
      Class<? extends AbstractEntity> myType = getEntityClass();
      if (type == null) {
        newId = newId.withType(myType);
      }
      assert (type == myType);
      long pk = newId.getId();
      if (pk == Id.ID_UUID) {
        this.primaryKey = null;
      } else {
        this.primaryKey = Long.valueOf(pk);
      }
      this.version = newId.getVersion();
      if (this.version == Id.VERSION_LATEST) {
        throw new IllegalArgumentException();
      }
    }
    this.id = newId;
  }

}
