/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.jpa.api.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.id.AbstractId;
import net.sf.mmm.util.data.api.id.Id;
import net.sf.mmm.util.data.base.id.LongVersionId;

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
      this.id = LongVersionId.of(getEntityClass(), this.primaryKey, Long.valueOf(this.version));
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

    if (this.id == id) {
      return;
    }
    this.id = AbstractId.getWithType(id, getEntityClass());
    this.primaryKey = AbstractId.getIdAs(this.id, Long.class);
    this.version = AbstractId.getVersionAsLong(this.id);
  }

}
