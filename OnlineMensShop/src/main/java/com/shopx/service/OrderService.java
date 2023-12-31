package com.shopx.service;

import java.util.List;

import com.shopx.dto.OrderRequestDTO;
import com.shopx.entities.Order;

public interface OrderService {
	
	public List<Order> getAllOrders();
	
	public List<Order> getOrderByCustomerId(Long custId);
	
	public Order addNewOrderItem(OrderRequestDTO order);
	
	public String deleteOrder(Long orderId);
	
	public Order getOrderByOrderId(Long orderId);
	
	
}
