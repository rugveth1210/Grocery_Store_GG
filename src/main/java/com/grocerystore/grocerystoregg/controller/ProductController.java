package com.grocerystore.grocerystoregg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocerystore.grocerystoregg.model.Product;
import com.grocerystore.grocerystoregg.service.ProductService;

@RestController
//@CrossOrigin(origins = "http://localhost:4300", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//CRUD
	@GetMapping("/product")
	private List<Product> getAllProducts(@RequestParam(name="page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name="size", required = false, defaultValue = "100000") Integer size){
		return productService.getAllProducts(page, size);
	}
	
	@PostMapping("/product")
	private Product postProduct(@RequestBody Product product) {
		return productService.postProduct(product);
	}
	
	@PostMapping("/products")
	private List<Product> postManyProducts(@RequestBody List<Product> products){
		return productService.postManyProducts(products);
	}
	
	@PutMapping("/product/{id}")
	private Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/product/{id}")
	private void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/product/{id}")
	private Product getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/category/{category}")
	private List<Product> getProductsByCategory(@RequestParam(name="page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name="size", required = false, defaultValue = "100000") Integer size, @PathVariable("category") String category){
		return productService.getProductsByCategory(page, size, category);
	}
	
	@GetMapping("/product/search")
	private List<Product> searchProduct(@RequestParam(name="q", required = true) String keyword){
		return productService.searchProduct(keyword);
	}
	
	@GetMapping("/product/quantity/{id}")
	private Long getProductQuantity(@PathVariable("id") Long id) {
		return productService.getProductQuantity(id);
	}
}
