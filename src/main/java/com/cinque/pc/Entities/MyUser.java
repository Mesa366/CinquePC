package com.cinque.pc.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class MyUser {

	/*TODO Agregar roles, ver si acá o en service*/

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String password;
	private String email;
	private Integer dni;
	private Integer phone;
	private Date birthday;
	private List<Product> SellingProduct;
	private List<Product> WishList;

	public MyUser() {

	}

	public MyUser(String name, String password, String email, Integer dni, Integer phone, Date birthday,
			List<Product> sellingProduct, List<Product> wishList) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
		this.birthday = birthday;
		SellingProduct = sellingProduct;
		WishList = wishList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Product> getSellingProduct() {
		return SellingProduct;
	}

	public void setSellingProduct(List<Product> sellingProduct) {
		SellingProduct = sellingProduct;
	}

	public List<Product> getWishList() {
		return WishList;
	}

	public void setWishList(List<Product> wishList) {
		WishList = wishList;
	}

	@Override
	public String toString() {
		return "MyUser [name=" + name + ", password=" + password + ", email=" + email + ", dni=" + dni + ", phone="
				+ phone + ", birthday=" + birthday + ", SellingProduct=" + SellingProduct + ", WishList=" + WishList
				+ ", getName()=" + getName() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
				+ ", getDni()=" + getDni() + ", getPhone()=" + getPhone() + ", getBirthday()=" + getBirthday()
				+ ", getSellingProduct()=" + getSellingProduct() + ", getWishList()=" + getWishList() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
