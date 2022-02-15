package com.cinque.pc.Entities;

import java.time.LocalDate;

/* TODO traducir al ingles
 * 	corregir detalles
 */

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;


/**
 * Entidad de Usuarios. Persistencia de usuarios. Tiene 2 fines: Comprador y
 * Vendedor.
 *
 * @author Martín Pavesio
 */

@Entity
@Table(name = "user")
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
	
	private String dni;
	private String phone;
   // @Temporal(javax.persistence.TemporalType.DATE)
	private LocalDate birthday;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Product> sellingProducts;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Product> wishList;
	

	public MyUser() {

	}

	public MyUser(String id, Image profilePicture, String name, String password, String email, String dni,
			String phone, LocalDate birthday, List<Product> sellingProduct, List<Product> wishList) {
		this.id = id;
		this.name = name;
		this.profilePicture = profilePicture;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
		this.birthday = birthday;
		this.sellingProducts = sellingProduct;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public List<Product> getSellingProducts() {
		return sellingProducts;
	}

	public void setSellingProduct(List<Product> sellingProduct) {
		this.sellingProducts = sellingProduct;
	}

	public List<Product> getWishList() {
		return wishList;
	}

	public void setWishList(List<Product> wishList) {
		this.wishList = wishList;
	}

}
