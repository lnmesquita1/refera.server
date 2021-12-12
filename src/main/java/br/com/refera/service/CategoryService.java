package br.com.refera.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.refera.entity.Category;

@Component
public interface CategoryService {
	
	Category save(Category category);

	List<Category> findAll();
	
}