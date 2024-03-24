package com.ecommerce.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Order1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    private double orderPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order1(Long orderId, double orderPrice, User user) {
		super();
		this.orderId = orderId;

		this.orderPrice = orderPrice;
		this.user = user;
	}

	public Order1() {
		super();
	}
    
    

    // Constructors, getters, and setters
}

