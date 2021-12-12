package br.com.refera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refera.entity.ReferaOrder;

public interface OrderRepository extends JpaRepository<ReferaOrder, Long> {

}
