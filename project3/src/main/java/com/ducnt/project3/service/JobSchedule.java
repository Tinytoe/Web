package com.ducnt.project3.service;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ducnt.project3.model.MessageDTO;
import com.ducnt.project3.repo.BillRepo;

@Component
public class JobSchedule {
	
	@Autowired
	BillRepo billRepo;
	
	@Autowired
	MailService mailService;
	
	@Scheduled(fixedDelay = 5 * 60 * 1000)
	public void notification() {
		
		MessageDTO messageDTO = new MessageDTO();
		
		Date now = new Date();
		long date1 = now.getTime() - ((long) 5 * 1000 * 60);
		Date date = new Date(date1);
		
		int size = billRepo.findByBuyDate(date).size();
		messageDTO.setContent(String.valueOf(size));
		
		try {
			mailService.sendEmailReport(messageDTO);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Sent email REPORT. Time: " + now);
		
//		List<Bill> listRp = billRepo.findByBuyDate(date);
		
	}
}
