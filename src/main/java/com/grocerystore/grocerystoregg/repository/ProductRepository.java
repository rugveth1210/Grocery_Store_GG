package com.grocerystore.grocerystoregg.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grocerystore.grocerystoregg.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategory(String category,  Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> searchProduct(String keyword);

}
