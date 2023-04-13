package com.training.messaging.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.application.from}")
    private String from;

    public void send(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        log.info("value of from: {}", from);

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
            log.info("Mail sent successfully..");
        } catch (Exception e) {
            log.error("Something went wrong.. Check logs");
            e.printStackTrace();
        }
    }
}
