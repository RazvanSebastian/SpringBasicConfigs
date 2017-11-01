package com.docs.spring.configurations;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfigurations {
	
	private static final Logger LOGGER = Logger.getLogger(PersistenceConfiguration.class);

	@Bean
	public EmbeddedServletContainerFactory servletContainerConfiguration() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(8081);
		factory.setSessionTimeout(10, TimeUnit.SECONDS);
		LOGGER.info("ServerConfigurations -> Bean TomcatEmbeddedServletContainerFactory initialized!");
		return factory;
	}
}
