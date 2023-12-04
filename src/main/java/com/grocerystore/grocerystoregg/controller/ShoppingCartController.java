package com.grocerystore.grocerystoregg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocerystore.grocerystoregg.model.CartItem;
import com.grocerystore.grocerystoregg.model.Product;
import com.grocerystore.grocerystoregg.model.User;
import com.grocerystore.grocerystoregg.repository.CartItemRepository;
import com.grocerystore.grocerystoregg.repository.ProductRepository;
import com.grocerystore.grocerystoregg.repository.UserRepository;
import com.grocerystore.grocerystoregg.service.ShoppingCartServices;

import jakarta.transaction.Transactional;



@RestController
//@CrossOrigin(origins="http://localhost:5923",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartServices shoppingCartServices;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	
	@GetMapping("/cart/{uid}")
	public List<CartItem> showShoppingCart(@PathVariable("uid") Long customerId) {
		User customer=userRepository.findById(customerId).orElse(null);
		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		return cartItems;
	}
	
	@PostMapping("/cart/{uid}/{pid}")
	public CartItem addProduct(@RequestBody CartItem cartItem,@PathVariable("uid") Long customerId,@PathVariable("pid") Long productId) {
		Product product=productRepository.findById(productId).orElse(null);
		User customer=userRepository.findById(customerId).orElse(null);
		cartItem.setQuantity(1L);
		cartItem.setSubTotal(product.getPrice()*cartItem.getQuantity());
		System.out.println(product.getPrice());
		System.out.println(product.getQuantity());
		cartItem.setUser(customer);
		cartItem.setProduct(product);
		return shoppingCartServices.addProduct(cartItem);
	}
	/*
	@PostMapping("/cart/post/{uid}/{pid}/{qty}")//edit this api as per S
	public Long addquantity(@PathVariable("pid") Long productId,
			@PathVariable("qty") Long quantity,@PathVariable("uid") Long customerId) {
		Customer customer=customerRepository.getById(customerId);
		Long addedQuantity=shoppingCartServices.addQuantity(productId, quantity, customer);
		return addedQuantity;
	}*/
	@PutMapping("/cart/put/{uid}/{pid}")//Post
	public CartItem addquantity(@PathVariable("pid") Long productId,
			@RequestBody CartItem cartItem,@PathVariable("uid") Long customerId) {
		User customer=userRepository.findById(customerId).orElse(null);
		return shoppingCartServices.putQuantity(productId, cartItem.getQuantity(), customer);
	}
	/*
	@PutMapping("/cart/update/{uid}/{pid}/{qty}")//put
	public Long updateQuantity(@PathVariable("pid") Long productId, 
			@PathVariable("qty") Long quantity,@PathVariable("uid") Long customerId) {
	
		Long subTotal=shoppingCartServices.updateQuantity(productId,quantity,customerId);
		return subTotal;
	}*/
	
	@Transactional
	@DeleteMapping("/cart/remove/{cid}")
	public void removeProductFromCart(@PathVariable("cid") Long cartId) {		
		shoppingCartServices.removeProduct(cartId);
	}
	
	@PostMapping("/product/post")
	public Product postProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping("/cart/get")
	public List<CartItem> getAllCartItems(){
		return cartItemRepository.findAll();
	}
	
	@GetMapping("/cart/sum/{uid}")
	public Long getCartSum(@PathVariable("uid") Long userId) {
		User customer=userRepository.findById(userId).orElse(null);
		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		Long sum = 0l;
		for(CartItem c:cartItems) {
			Long sumLong=shoppingCartServices.cartSum(c,userId);
			sum = sum+sumLong;
		}

		return sum;
	}
}