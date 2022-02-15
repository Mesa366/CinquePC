package com.cinque.pc.Entities;

import java.time.LocalDate;

/* TODO traducir al ingles
 * 	corregir detalles
 */

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

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

	@OneToOne
	private MyUser seller;

	@OneToOne
	private Image photo;
	
	@OneToOne
	private MyUser buyer;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private MyUser user;

	@Temporal(TemporalType.DATE)
	private LocalDate sellingDate;

	@Temporal(TemporalType.DATE)
	private LocalDate buyingDate;
	
	private Integer stock;
	private Boolean enabled;
	
	private String category;

	public Product(String id, String name, Double price, MyUser seller, Image photo, MyUser buyer, MyUser user,
			LocalDate sellingDate, LocalDate buyingDate, Integer stock, Boolean enabled, String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.photo = photo;
		this.buyer = buyer;
		this.user = user;
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

	public MyUser getBuyer() {
		return buyer;
	}

	public void setBuyer(MyUser buyer) {
		this.buyer = buyer;
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

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
