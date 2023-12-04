package com.grocerystore.grocerystoregg.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name="orderdata")
public class OrderData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long orderId;
	
	@ManyToMany
	@JoinTable(name = "cartItem_orderdata",joinColumns = @JoinColumn(name="orderdata_id"),
	inverseJoinColumns = @JoinColumn(name="cartItem_id"))
	private List<CartItem> cartItem;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "OrderData [orderId=" + orderId + ", cartItem=" + cartItem + "]";
	}

}
