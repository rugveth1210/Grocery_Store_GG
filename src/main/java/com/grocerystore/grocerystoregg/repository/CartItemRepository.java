package com.grocerystore.grocerystoregg.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grocerystore.grocerystoregg.model.CartItem;
import com.grocerystore.grocerystoregg.model.Product;
import com.grocerystore.grocerystoregg.model.User;

import jakarta.transaction.Transactional;



@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	public List<CartItem> findByUser(User customer);
	
	
	public CartItem findByUserAndProduct(User customer,Product product);

	@Query("update CartItem c set c.quantity = ?1 where c.product.id=?2 AND c.user.id=?3")
	@Modifying
	public void updateQuantity(Long quantity, Long productId, Long customerId);
	

	public void deleteById(Long cartId);


	@Modifying
	@Transactional
	@Query("delete from CartItem c where c.user.id=?1")
	public void deleteAll(Long uid);
	

}