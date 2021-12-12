package br.com.refera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.refera.entity.Category;
import br.com.refera.repository.CategoryRepository;
import br.com.refera.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
}
