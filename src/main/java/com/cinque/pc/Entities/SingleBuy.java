package com.cinque.pc.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SingleBuy {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "userShoppingCart")
	private MyUser userShoppingCart;
	
	@ManyToOne
	@JoinColumn(name = "userShoppingHistory")
	private MyUser userShoppingHistory;
	
	@ManyToOne
	@JoinColumn(name = "userSellingProducts")
	private MyUser userSellingProducts;
	
	@Column(nullable = false)
	private Integer quantity;
		
	@OneToOne
	private Product product;
	
	public SingleBuy() {}
	
	public SingleBuy(String id, MyUser userShoppingCart, MyUser userShoppingHistory, MyUser userSellingProducts,
			Integer quantity, Product product) {
		super();
		this.id = id;
		this.userShoppingCart = userShoppingCart;
		this.userShoppingHistory = userShoppingHistory;
		this.userSellingProducts = userSellingProducts;
		this.quantity = quantity;
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MyUser getUserShoppingCart() {
		return userShoppingCart;
	}

	public void setUserShoppingCart(MyUser userShoppingCart) {
		this.userShoppingCart = userShoppingCart;
	}

	public MyUser getUserShoppingHistory() {
		return userShoppingHistory;
	}

	public void setUserShoppingHistory(MyUser userShoppingHistory) {
		this.userShoppingHistory = userShoppingHistory;
	}

	public MyUser getUserSellingProducts() {
		return userSellingProducts;
	}

	public void setUserSellingProducts(MyUser userSellingProducts) {
		this.userSellingProducts = userSellingProducts;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
