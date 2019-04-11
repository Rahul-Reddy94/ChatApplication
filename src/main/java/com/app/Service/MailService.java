package com.app.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class MailService {
	
	private TemplateEngine engine;
	
	@Autowired
	JavaMailSender mailSender;

	@Value("${mail.smtp.enable}")
	private Boolean enable;

	@Value("${site.url}")	
	private String url;

	@Autowired
	public MailService(TemplateEngine engine) {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("templates/");
		resolver.setSuffix(".html");
		resolver.setCacheable(false);
		resolver.setTemplateMode("HTML5");
		engine.setTemplateResolver(resolver);
		this.engine = engine;
	}
	
	@Async
	public void sendVerficationEmail(String email, String token) {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Context context = new Context();
		context.setVariable("name", "Weekend");
		context.setVariable("token", token);
		context.setVariable("url", url);
		
		String emailContent = this.engine.process("verifyemail", context);
		System.out.println("Printing "+ emailContent);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setSubject("Please verify the mail");
                helper.setFrom(new InternetAddress("no-reply@sincetesting.com"));
                helper.setTo(email);
                helper.setText(emailContent);
			}
		};

			send(preparator);

	}

	private void send(MimeMessagePreparator preparator) {  
		if(enable) { // shouold change this one in properties file
			try {
				mailSender.send(preparator);
			} catch (MailException e) {
				e.printStackTrace(); 
			}
		}
	}

	
}
