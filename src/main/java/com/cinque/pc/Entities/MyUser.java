package com.cinque.pc.Entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


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

	private LocalDate birthday;

	
	@OneToMany(
			mappedBy = "userSellingProducts",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<SingleBuy> sellingProducts;

	@OneToMany(
			mappedBy = "userWishList",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Product> wishList;
	
	@OneToMany(
			mappedBy = "userShoppingCart",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<SingleBuy> shoppingCart;
	
	//Change made by Bombón Bellota y burbuja(alan y cia)
	@OneToMany(
			mappedBy = "userShoppingHistory",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<SingleBuy> shoppingHistory;
	
	private Double wallet;	

	public MyUser() {

	}

	

	public MyUser(String id, Image profilePicture, String name, String password, String email, String dni, String phone,
			LocalDate birthday, List<SingleBuy> sellingProducts, List<Product> wishList, List<SingleBuy> shoppingCart,
			List<SingleBuy> shoppingHistory, Double wallet) {
		super();
		this.id = id;
		this.profilePicture = profilePicture;
		this.name = name;
		this.password = password;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
		this.birthday = birthday;
		this.sellingProducts = sellingProducts;
		this.wishList = wishList;
		this.shoppingCart = shoppingCart;
		this.shoppingHistory = shoppingHistory;
		this.wallet = wallet;
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

	public List<SingleBuy> getSellingProducts() {
		return sellingProducts;
	}

	public void setSellingProduct(List<SingleBuy> sellingProduct) {
		this.sellingProducts = sellingProduct;
	}

	public List<Product> getWishList() {
		return wishList;
	}

	public void setWishList(List<Product> wishList) {
		this.wishList = wishList;
	}

	public List<SingleBuy> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<SingleBuy> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Double getWallet() {
		return wallet;
	}

	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}

	public void setSellingProducts(List<SingleBuy> sellingProducts) {
		this.sellingProducts = sellingProducts;
	}

	public List<SingleBuy> getShoppingHistory() {
		return shoppingHistory;
	}

	public void setShoppingHistory(List<SingleBuy> shoppingHistory) {
		this.shoppingHistory = shoppingHistory;
	}



	@Override
	public String toString() {
		return "MyUser [id=" + id + ", profilePicture=" + profilePicture + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", dni=" + dni + ", phone=" + phone + ", birthday=" + birthday
				+ ", sellingProducts=" + sellingProducts + ", wishList=" + wishList + ", shoppingCart=" + shoppingCart
				+ ", shoppingHistory=" + shoppingHistory + ", wallet=" + wallet + "]";
	}
	
	

}
