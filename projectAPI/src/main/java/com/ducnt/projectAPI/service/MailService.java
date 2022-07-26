package com.ducnt.projectAPI.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ducnt.projectAPI.model.MessageDTO;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	SpringTemplateEngine templateEngine;

	public void sendEmail(MessageDTO messageDTO) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

		// load template email with content
		Context context = new Context();
		context.setVariable("toName", messageDTO.getToName());
		context.setVariable("content", messageDTO.getContent());
		String html = templateEngine.process("hi.html", context);

		/// send mail
		helper.setTo(messageDTO.getTo());
		helper.setText(html, true);
		helper.setSubject(messageDTO.getSubject());
		helper.setFrom(messageDTO.getFrom());
		javaMailSender.send(message);
	}

	public void sendEmailReport(MessageDTO messageDTO) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

		// load template email with content
		Context context = new Context();
		context.setVariable("toName", "Sếp");
		context.setVariable("content", messageDTO.getContent());
		String html = templateEngine.process("report.html", context);

		/// send mail
		helper.setTo("0877288886duc@gmail.com");
		helper.setText(html, true);
		helper.setSubject("Báo cáo tình trạng hoá đơn");
		helper.setFrom(messageDTO.getFrom());
		javaMailSender.send(message);

	}
	
	public void sendEmailPassword(MessageDTO messageDTO) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

		// load template email with content
		Context context = new Context();
		context.setVariable("toName", messageDTO.getToName());
		context.setVariable("content", messageDTO.getContent());
		String html = templateEngine.process("password-customer.html", context);

		/// send mail
		helper.setTo(messageDTO.getTo());
		helper.setText(html, true);
		helper.setSubject("THAY ĐỔI MẬT KHẨU THÀNH CÔNG");
		helper.setFrom(messageDTO.getFrom());
		javaMailSender.send(message);

	}
	
	
}
