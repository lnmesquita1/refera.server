package br.com.refera.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.refera.entity.ReferaOrder;

@Component
public interface OrderService {

	ReferaOrder save(ReferaOrder order);
	
	List<ReferaOrder> findAll();
	
}