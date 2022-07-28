package com.ducnt.projectdemo.entity;

import java.util.List;

import lombok.Data;
@Data
public class Order {
	private List<OrderItem> itemDTOs;
}
