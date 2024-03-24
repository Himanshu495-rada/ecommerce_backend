package com.ecommerce.ecommerce.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String addressDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address(Long addressId, String addressDetail, User user) {
		super();
		this.addressId = addressId;
		this.addressDetail = addressDetail;
		this.user = user;
	}

	public Address() {
		super();
	}

    
    // Constructors, getters, and setters
}

