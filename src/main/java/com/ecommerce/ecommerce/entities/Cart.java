package com.ecommerce.ecommerce.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long totalAmount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart(Long cartId, Long totalAmount, User user) {
		super();
		this.cartId = cartId;
		this.totalAmount = totalAmount;
		this.user = user;
	}

	public Cart() {
		super();
	}
    
    
    // Constructors, getters, and setters
}

