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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The main objective of this class is handle the Spring
 * configuration capabilities for this module. As it is a spring boot JPA project this class use the
 * EnableJpaRepositories to Set and establish the components to handle the DB connections
 * and configurations.
 *
 */
@Configuration
@ComponentScan("com.centric.products.domain")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryProducts",
    transactionManagerRef = "transactionManagerProducts",
    basePackages = {
    "com.centric.products.repository" })
public class PersistenceServiceConfig {
	
	@Autowired
	DataSource dataSource;


  /**
   * Configuration method to create the EntityManagerFactory. We created the factory using as a
   * parameters the EntityManagerFactoryBuilder class from Spring and the Datasource created
   * in this configuration class. Also The method referenced the entity package in order that
   * the JPA engine search all entities for the domain.
   *
   * @param builder
   *          the entity manager using spring Data
   * @return LocalContainerEntityManagerFactoryBean with the configuration ready to use.
   */
  @Bean(name = "entityManagerFactoryProducts")
  @Qualifier("entityManagerFactoryProducts")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryProducts(
      EntityManagerFactoryBuilder builder) {
    return builder.dataSource(dataSource).packages("com.centric.products.domain")
        .persistenceUnit("products").build();
  }

  /**
   * This method create the transaction manager using the PlatformTransactionManager from
   * Spring framework implementation for a single JPA EntityManagerFactory. Binds a JPA
   * EntityManager from the specified factory to the thread, potentially allowing for one
   * thread-bound EntityManager per factory.
   *
   * @param entityManagerFactory
   *          the entityManagerFactory for Centric DB.
   * @return the PlatformTransactionManager.
   */
  @Bean(name = "transactionManagerProducts")
  @Qualifier("transactionManagerProducts")
  public PlatformTransactionManager transactionManagerProducts(
      @Qualifier("entityManagerFactoryProducts") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
