/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.jpa.impl.query.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.sf.mmm.bean.impl.spring.BeanSpringConfig;
import net.sf.mmm.jpa.api.query.statenent.jpql.JpqlStatementFactory;
import net.sf.mmm.jpa.impl.query.statement.jpql.JpqlStatementFactoryImpl;

/**
 * This is the Spring {@link Configuration} for {@link net.sf.mmm.util.pojo}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Configuration
@Import(BeanSpringConfig.class)
@SuppressWarnings("javadoc")
public class UtilQueryJpqlSpringConfig {

  @Bean
  public JpqlStatementFactory statementFactory() {

    return new JpqlStatementFactoryImpl();
  }

}
