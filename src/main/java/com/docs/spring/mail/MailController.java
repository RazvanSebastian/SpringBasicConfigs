package com.docs.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	@Autowired
	private SimpleMessageComponent simpleMessageComponent;

	@PostMapping(value = "/mail")
	@ResponseStatus(value = HttpStatus.OK)
	public void sendEmail(@RequestBody(required = true) MailTemplate mailTemplate) {
		simpleMessageComponent.sendMail(mailTemplate);
	}
}
