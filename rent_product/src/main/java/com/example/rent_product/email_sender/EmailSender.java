package com.example.rent_product.email_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String mailName, String text, String sendTo) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shi-java5@yandex.ru");
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(mailName);

        mailSender.send(simpleMailMessage);
    }

}
