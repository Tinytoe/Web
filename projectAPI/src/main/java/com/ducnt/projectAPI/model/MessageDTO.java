package com.ducnt.projectAPI.model;

import lombok.Data;

@Data
public class MessageDTO {
	
	final String from = "duc11399nt@gmail.com";
	private String to;
	private String toName;
	final String subject = "Thông báo đơn hàng";
	private String content;
	
}
