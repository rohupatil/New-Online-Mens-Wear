package com.shopx.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopx.dao.PaymentDao;
import com.shopx.dto.PaymentRequestDTO;
import com.shopx.entities.Order;
import com.shopx.entities.Payment;
import com.shopx.entities.User;



@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao dao;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Payment> getAllPaymentDetails() {
		return dao.findAll();
	}

	@Override
	public Payment addNewPaymentDetails(PaymentRequestDTO dto) {
		User customer = customerService.getCustomerById(dto.getCustomerId());
		Order order = orderService.getOrderByOrderId(dto.getOrderId());
		Payment payment = mapper.map(dto, Payment.class);
		payment.setCustomer(customer);
		payment.setOrder(order);
		return dao.save(payment);
	}

}
