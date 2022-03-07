package com.cinque.pc.Entities;

import java.time.LocalDate;

/* TODO traducir al ingles
 * 	corregir detalles
 */


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.cinque.pc.Enums.Categories;

/**
 * Entidad de Productos.
 */

@Entity
public class Product {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private Double price;
	private String keyword; //Agregue este atributo para las busquedas

	@OneToOne
	private MyUser seller;

	//TODO esto no es OneToOne
	@OneToOne
	private Image photo;
	
	//For privacy reasons, and because the stock attribute this can be a Many to Many
	//We are going to delete this attribute from Product, and leave it in user.
	//Atte: Ana, Esteban y Alan
	/*
	@OneToOne
	private MyUser buyer;
	*/
	@ManyToOne
	@JoinColumn(name = "userWishList_id")
	private MyUser userWishList; //Deberia estar solo este porque los otros 3 los tiene SingleBuy
	
	@ManyToOne
	@JoinColumn(name = "userSellingProducts_id")
	private MyUser userSellingProducts;

	@ManyToOne
	@JoinColumn(name = "userShoppingCart_id")
	private MyUser userShoppingCart;
	
	@ManyToOne
	@JoinColumn(name = "userShoppingHistory_id")
	private MyUser userShoppingHistory;
	
	//@Temporal(TemporalType.DATE)
	private LocalDate sellingDate;

	//@Temporal(TemporalType.DATE)
	private LocalDate buyingDate;
	
	private Integer stock;
	private Boolean enabled;
	
	@Enumerated(EnumType.STRING)
	private Categories category;
	
	public Product(String id, String name, Double price, MyUser seller, Image photo, MyUser userWishList,
			MyUser userSellingProducts, MyUser userShoppingCart, MyUser userShoppingHistory, LocalDate sellingDate,
			LocalDate buyingDate, Integer stock, Boolean enabled, Categories category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.photo = photo;
		this.userWishList = userWishList;
		this.userSellingProducts = userSellingProducts;
		this.userShoppingCart = userShoppingCart;
		this.userShoppingHistory = userShoppingHistory;
		this.sellingDate = sellingDate;
		this.buyingDate = buyingDate;
		this.stock = stock;
		this.enabled = enabled;
		this.category = category;
	}

	public Product() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public MyUser getSeller() {
		return seller;
	}

	public void setSeller(MyUser seller) {
		this.seller = seller;
	}

	public LocalDate getSellingDate() {
		return sellingDate;
	}

	public void setSellingDate(LocalDate sellingDate) {
		this.sellingDate = sellingDate;
	}

	public LocalDate getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public MyUser getUserWishList() {
		return userWishList;
	}

	public void setUserWishList(MyUser userWishList) {
		this.userWishList = userWishList;
	}

	public MyUser getUserSellingProducts() {
		return userSellingProducts;
	}

	public void setUserSellingProducts(MyUser userSellingProducts) {
		this.userSellingProducts = userSellingProducts;
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
	
	
	
}
