package com.docs.spring.configurations;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.docs.spring.repository" })
public class PersistenceConfiguration {
	
	private static final Logger LOGGER = Logger.getLogger(PersistenceConfiguration.class);
	
	@Bean
	DataSource dataSource(Environment env) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
		hikariConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
		hikariConfig.setUsername(env.getRequiredProperty("db.username"));
		hikariConfig.setPassword(env.getRequiredProperty("db.password"));
		LOGGER.info("PersistenceConfiguration -> Bean DataSource initialized!");
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.docs.spring.model");

		Properties jpaProperties = new Properties();

		// // Configures the used database dialect. This allows Hibernate to create SQL
		// // that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		//
		// // Specifies the action that is invoked to the database :
		// // create-drop, create, update, inspect
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		//
		// // If the value of this property is true, Hibernate writes all SQL
		// // statements to the console.
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		//
		// // If the value of this property is true, Hibernate will format the SQL
		// // that is written to the console.
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		
		LOGGER.info("PersistenceConfiguration -> Bean LocalContainerEntityManagerFactoryBean initialized!");
		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		LOGGER.info("PersistenceConfiguration -> Bean JpaTransactionManager initialized!");
		return transactionManager;
	}

}
