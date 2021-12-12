package br.com.refera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.refera.entity.ReferaOrder;
import br.com.refera.response.Response;
import br.com.refera.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping()
    public ResponseEntity<Response<ReferaOrder>> createOrder(@RequestBody ReferaOrder order) {
		Response<ReferaOrder> response = new Response<ReferaOrder>();
		try {
			ReferaOrder persisted = orderService.save(order);
			response.setData(persisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="all")
	public ResponseEntity<Response<List<ReferaOrder>>> findAll() {
		Response<List<ReferaOrder>> response = new Response<List<ReferaOrder>>();
		List<ReferaOrder> orders = orderService.findAll();
		response.setData(orders);
		return ResponseEntity.ok(response);
	}
	
	
}
