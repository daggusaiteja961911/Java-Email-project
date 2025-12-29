package com.saiteja.java_mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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
	
	@RequestMapping("/send-html-email")
	public String sendHtmlEmailApi() {
	    try {
	        sendHtmlEmail(
	                "nitwarangal219@gmail.com",
	                "Sai Teja",
	                "456789"
	        );
	        return "HTML Email sent successfully!";
	    } catch (Exception e) {
	        return e.getMessage();
	    }
	}

	
	
	public String loadEmailTemplate(String name, String otp) throws IOException {
	    String html = Files.readString(
	        Paths.get("src/main/resources/templates/email-template.html")
	    );
	    html = html.replace("${name}", name);
	    html = html.replace("${otp}", otp);

	    return html;
	}

	public void sendHtmlEmail(String to, String name, String otp) throws Exception {
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper =
	            new MimeMessageHelper(message, true, "UTF-8");

	    helper.setFrom("nitwarangal219@gmail.com");
	    helper.setTo(to);
	    helper.setSubject("Your OTP Verification");
	    helper.setText(loadEmailTemplate(name, otp), true);

	    mailSender.send(message);
	}



}
