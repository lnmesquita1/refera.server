package br.com.refera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.refera.entity.ReferaOrder;
import br.com.refera.repository.OrderRepository;
import br.com.refera.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<ReferaOrder> findAll() {
		return orderRepository.findAll(Sort.by(Direction.ASC, "id"));
	}
	
	@Override
	public ReferaOrder save(ReferaOrder order) {
		return orderRepository.save(order);
	}
}
