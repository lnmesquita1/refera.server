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

import br.com.refera.entity.Category;
import br.com.refera.response.Response;
import br.com.refera.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping()
    public ResponseEntity<Response<Category>> createOrder(@RequestBody Category category) {
		Response<Category> response = new Response<Category>();
		try {
			Category persisted = categoryService.save(category);
			response.setData(persisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="all")
	public ResponseEntity<Response<List<Category>>> findAll() {
		Response<List<Category>> response = new Response<List<Category>>();
		List<Category> categories = categoryService.findAll();
		response.setData(categories);
		return ResponseEntity.ok(response);
	}

}
