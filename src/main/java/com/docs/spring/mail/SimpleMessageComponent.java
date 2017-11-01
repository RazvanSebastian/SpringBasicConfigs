package com.docs.spring.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageComponent {

	private static final Logger LOGGER = Logger.getLogger(SimpleMessageComponent.class);

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(MailTemplate template) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(template.getFrom());
		simpleMailMessage.setTo(template.getTo());
		simpleMailMessage.setSubject(template.getSubject());
		simpleMailMessage.setText(template.getBody());
		try {
			this.mailSender.send(simpleMailMessage);
			LOGGER.info("Mail successfully sent!");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}
}
