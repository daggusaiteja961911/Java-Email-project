package com.saiteja.java_mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.internet.MimeMessage;

@RestController
public class EmailController {
	
	private final JavaMailSender mailSender;
	
	public EmailController(JavaMailSender javaMailSender) {
		this.mailSender = javaMailSender;
	}

	@RequestMapping("/send-email")
	public String sendEmail() {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("nitwarangal219@gmail.com");
			message.setTo("nitwarangal219@gmail.com");
			message.setSubject("Test Email from Spring Boot");
			message.setText("Hello, this is a test email sent from a Spring Boot application!");
			mailSender.send(message);
			
			return "Email sent successfully!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
	}
	
	@RequestMapping("/send-email-with-attachment")
	public String sendEmailWithAttachment() {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("nitwarangal219@gmail.com");
			helper.setTo("nitwarangal219@gmail.com");
			helper.setSubject("Test Email with Attachment from Spring Boot");
			helper.setText("Hello, this is a test email with attachment sent from a Spring Boot application!");
			helper.addAttachment("generated-image (1).png", new java.io.File("C:\\Users\\Sai Teja\\Desktop\\Genrated AI images\\generated-image (1).png"));
			mailSender.send(message);
			return "Email with attachment sent successfully!";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
}
