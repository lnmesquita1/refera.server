package br.com.refera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refera.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
