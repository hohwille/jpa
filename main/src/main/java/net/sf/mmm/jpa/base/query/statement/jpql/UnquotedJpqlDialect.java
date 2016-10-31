/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.jpa.base.query.statement.jpql;

/**
 * {@link JpqlDialect} that does not {@link #quoteReference() quote} {@link #ref(String) references}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class UnquotedJpqlDialect extends JpqlDialect {

  @Override
  public String quoteReference() {

    return "";
  }

}
