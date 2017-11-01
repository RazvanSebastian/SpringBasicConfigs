package com.docs.spring.mail;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.docs.spring.configurations.PersistenceConfiguration;

/*
 * @NOTE!!!
 * If the OAuth2 security it's not implemented you must allow to access your mail account 
 * application less secured accessing link bellow :
 * https://myaccount.google.com/lesssecureapps
 */

@Configuration
public class MailConfiguration {

	private static final Logger LOGGER = Logger.getLogger(PersistenceConfiguration.class);

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("photo.book.api@gmail.com");
		mailSender.setPassword("bl@n@100");
		mailSender.setJavaMailProperties(mailProperties());
		LOGGER.info("MailConfiguration -> Bean JavaMailSender initialized!");

		return mailSender;
	}

	Properties mailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		return properties;
	}
}
