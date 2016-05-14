/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.jpa.api.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import net.sf.mmm.util.bean.api.entity.Entity;
import net.sf.mmm.util.bean.api.id.Id;
import net.sf.mmm.util.bean.api.id.PrimaryKey;

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
      this.id = PrimaryKey.valueOf(getClass(), this.primaryKey.longValue(), this.version);
    }
    return this.id;
  }

  @Override
  public void setId(Id<?> id) {

    if (id == null) {
      this.primaryKey = null;
      this.version = 0;
    } else {
      assert (id.getType() == getClass());
      this.primaryKey = Long.valueOf(id.getId());
      this.version = id.getVersion();
      if (this.version == Id.VERSION_LATEST) {
        throw new IllegalArgumentException();
      }
    }
    this.id = id;
  }

}
