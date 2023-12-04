package com.grocerystore.grocerystoregg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerystore.grocerystoregg.model.CartItem;
import com.grocerystore.grocerystoregg.model.Product;
import com.grocerystore.grocerystoregg.model.User;
import com.grocerystore.grocerystoregg.repository.CartItemRepository;
import com.grocerystore.grocerystoregg.repository.ProductRepository;
import com.grocerystore.grocerystoregg.repository.UserRepository;



@Service
public class ShoppingCartServices {
	
	@Autowired
	public CartItemRepository cartItemRepository;
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	
	public List<CartItem> listCartItems(User customer){
		return cartItemRepository.findByUser(customer);
	}
	/*
	public Long addQuantity(Long productId, Long quantity, Customer customer) {
		Long addedQuantity=quantity;
		
		Product product=productRepository.getById(productId);
		
		CartItem cartItem=cartItemRepository.findByCustomerAndProduct(customer, product);
		
		if(cartItem !=null) {
			addedQuantity=cartItem.getQuantity()+quantity;
			cartItem.setQuantity(addedQuantity);
		}else {
			cartItem=new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setCustomer(customer);
			cartItem.setProduct(product);
		}
		
		cartItemRepository.save(cartItem);
		
		return addedQuantity;
	}*/
	public CartItem putQuantity(Long productId, Long quantity, User customer) {//edit
		long addedQuantity=quantity;
		
		Product product=productRepository.findById(productId).orElse(null);
		
		CartItem cartItem=cartItemRepository.findByUserAndProduct(customer, product);
		
		cartItem.setQuantity(addedQuantity);
		cartItem.setSubTotal(quantity*product.getPrice());
		CartItem c=cartItemRepository.save(cartItem);
		
		return c;
		
	}

	public long updateQuantity(Long productId, Long quantity, Long customerId) {
		cartItemRepository.updateQuantity(quantity,productId,customerId);
		Product product=productRepository.findById(productId).orElse(null);
		long subTotal=product.getPrice()*quantity;
		return subTotal;
	}

	public void removeProduct(Long cartId) {
		cartItemRepository.deleteById(cartId);
	}

	public CartItem addProduct(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	public Long cartSum(CartItem c, Long userId) {
		// TODO Auto-generated method stub
		Long subTotal=c.getSubTotal();
		return subTotal;
	}
	
	public void deleteAllCartItems(Long uid) {
		cartItemRepository.deleteAll(uid);
	}
	
	
}
