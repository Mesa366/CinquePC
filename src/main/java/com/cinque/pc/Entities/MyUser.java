package com.cinque.pc.Entities;

/* TODO traducir al ingles
 * 	corregir detalles
 */

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


/**
 * Entidad de Usuarios. Persistencia de usuarios. Tiene 2 fines: Comprador y
 * Vendedor.
 *
 * @author Martín Pavesio
 */

@Entity
public class MyUser {

	/* TODO Agregar roles, ver si acá o en service */

	/**
	 * Estrategia seleccionada GeneratedValue por defecto. Si falla, usa
	 * GenericGenerator. Garantiza un ID único e irrepetible rápidamente.
	 * 
	 * @param id             UUID autogenerada
	 * @param sellingProduct Lista de productos (catálogo) del vendedor.
	 * @param wishList       Lista de productos deseados para el comprador.
	 * @param email - It requires a column = unique because in an user system we need a unique parameter to login/register  
	 */

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@OneToOne
	private Image profilePicture;

	private String name;
	private String password;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private Integer dni;
	private Integer phone;
	private Date birthday;
	private List<Product> sellingProduct;
	private List<Product> wishList;

	public MyUser() {

	}

	public MyUser(String id, Image profilePicture, String name, String password, String email, Integer dni,
			Integer phone, Date birthday, List<Product> sellingProduct, List<Product> wishList) {
		this.id = id;
		this.name = name;
		this.profilePicture = profilePicture;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
		this.birthday = birthday;
		this.sellingProduct = sellingProduct;
		this.wishList = wishList;
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

	public Image getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
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
		return sellingProduct;
	}

	public void setSellingProduct(List<Product> sellingProduct) {
		this.sellingProduct = sellingProduct;
	}

	public List<Product> getWishList() {
		return wishList;
	}

	public void setWishList(List<Product> wishList) {
		this.wishList = wishList;
	}

}
