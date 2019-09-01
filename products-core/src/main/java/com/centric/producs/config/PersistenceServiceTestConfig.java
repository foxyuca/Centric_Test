/* Copyright (c) 2018 XXXXXX, Inc.
 *
 * This unpublished material is proprietary to XXXXXX.
 * All rights reserved. The methods and techniques described
 * herein are considered trade secrets and/or confidential.
 * Reproduction or distribution, in whole or in part, is
 * forbidden except by express written permission of
 * XXXXXX.
 */

package com.centric.producs.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * The main objective of this class is handle the Spring configuration capabilities for this module.
 * As it is a spring boot JPA project this class use the EnableJpaRepositories to Set and establish
 * the components to handle the DB connections and configurations.
 */
@Configuration
@Profile("development")
public class PersistenceServiceTestConfig extends PersistenceServiceConfig {


  /**
   * Override for testing to append custom properties for JPA.
   *
   * @param builder
   *          the entity manager using spring Data
   * @param dataSource
   *          the Data source created for PRODUCTS DB.
   * @return LocalContainerEntityManagerFactoryBean with the configuration ready to use.
   */
  @Bean(name = "entityManagerFactoryProducts")
  @Qualifier("entityManagerFactoryProducts")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryProducts(
      EntityManagerFactoryBuilder builder, @Qualifier("productsDataSource") DataSource dataSource) {
    Map<String, String> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    return builder.dataSource(dataSource).packages("com.centric.products.domain")
        .properties(jpaProperties).persistenceUnit("products").build();
  }

}