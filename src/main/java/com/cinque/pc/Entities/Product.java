package com.cinque.pc.Entities;

/* TODO traducir al ingles
 * 	corregir detalles
 */

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad de Productos. Sarasa
 *
 * @author David Gonzalez
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
	private MyUser buyer;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private MyUser user;

	@Temporal(TemporalType.DATE)
	private Date sellingDate;

	@Temporal(TemporalType.DATE)
	private Date buyingDate;
	
	private Integer stock;
	private Boolean enabled;

	public Product(String id, String name, Double price, MyUser seller, Date sellingDate, Date buyingDate,
			Integer stock, MyUser buyer, Boolean enabled) {

		this.id = id;
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.sellingDate = sellingDate;
		this.buyingDate = buyingDate;
		this.stock = stock;
		this.buyer = buyer;
		this.enabled = enabled;
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

	public Date getSellingDate() {
		return sellingDate;
	}

	public void setSellingDate(Date sellingDate) {
		this.sellingDate = sellingDate;
	}

	public Date getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(Date buyingDate) {
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

}
