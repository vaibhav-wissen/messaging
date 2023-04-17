package com.training.messaging;

import com.training.messaging.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MessagingApplication {

	@Autowired
	private EmailService emailService;

	@Value("${spring.application.to}")
	private String to;
	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);

	}
	/*@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {

		emailService.send(to,
				"Some good subject",
				"This is my final message to you");
	}
	 */

}
