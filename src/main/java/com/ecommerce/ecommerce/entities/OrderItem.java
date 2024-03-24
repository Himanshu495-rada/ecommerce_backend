package com.ecommerce.ecommerce.entities;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order1 order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order1 getOrder() {
		return order;
	}

	public void setOrder(Order1 order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem(Long orderItemId, int quantity, Order1 order, Product product) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public OrderItem() {
		super();
	}
    
    

    // Constructors, getters, and setters
}
