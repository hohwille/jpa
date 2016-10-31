/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.query.base.example;

import javax.persistence.Entity;

import net.sf.mmm.jpa.api.entity.AbstractEntity;

/**
 * Implementation of {@link Contact} as JPA {@link Entity}.
 */
@Entity
public class ContactEntity extends AbstractEntity implements Contact {

  private String firstName;

  private String lastName;

  private Integer age;

  private boolean friend;

  private ContactType type;

  @Override
  public String getFirstName() {

    return this.firstName;
  }

  @Override
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  @Override
  public String getLastName() {

    return this.lastName;
  }

  @Override
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  @Override
  public Integer getAge() {

    return this.age;
  }

  @Override
  public void setAge(Integer age) {

    this.age = age;
  }

  @Override
  public boolean isFriend() {

    return this.friend;
  }

  @Override
  public void setFriend(boolean friend) {

    this.friend = friend;
  }

  @Override
  public ContactType getType() {

    return this.type;
  }

  @Override
  public void setType(ContactType type) {

    this.type = type;
  }

}
