package com.cinque.pc.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cinque.pc.Entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Enums.Categories;
import com.cinque.pc.Repositories.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Validator validator;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private MyUserService userService;

	//CREATE
	/**
	 * Creates product
        * @param name
        * @param price
        * @param user seller
        * @param stock
        * @param category
        * @param product's picture
        * @throws java.lang.Exception
	 */
	public void createProduct (String name, Double price, MyUser seller, Integer stock, Categories category, MultipartFile picture) throws Exception {
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		
		Product product = new Product(); 
		
		product.setName(name);
		product.setPrice(price);
		product.setSeller(seller);
		//product.setSellingDate(sellingDate);
		product.setStock(stock);
		seller.getSellingProducts().add(product);
		product.setCategory(category);
		//Transform from MultipartFile to Image, so we can set it to the attribute
		Image photo = imageService.saveImage(picture);
		product.setPhoto(photo);
		productRepository.save(product);
		
	}
	//UPDATE
	/**
	 * Updates product data
        * @param name
        * @param price
        * @param user seller
        * @param stock
        * @param category
        * @throws java.lang.Exception
	 */
	public void editProduct(String id, String name, Double price,Integer stock, Categories category) throws Exception{
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		
		Product product = productRepository.getById(id); 
		
		product.setName(name);
		product.setPrice(price);
		product.setCategory(category);
		product.setStock(stock);
		
		productRepository.save(product);
		
	}
	
	//UPDATE PRODUCT PICTURE
	/**
	 * Only updates product's profile picture. 
	 * @param picture It's the MultipartFile coming from the frontend.
	 * @param id It's the product's id. 
	 */
	public void updatePicture(MultipartFile picture, String id) throws Exception {
		Product product = productRepository.getById(id); 
		//Transform from MultipartFile to Image, so we can set it to the attribute
		Image photo = imageService.saveImage(picture);
		product.setPhoto(photo);
		productRepository.save(product);
	}
	
	//CHANGE PRODUCT STATUS
	/*
	 * Set the product status (enabled/disabled)
	 */
	public void productStatus(String id) throws Exception{
		Product product = productRepository.getById(id); 
		product.setEnabled(!product.getEnabled());
		productRepository.save(product);
		
	}
	
	public void addToCart(MyUser user, Product product) throws Exception {
		validator.stringValidate(user.getId(), "UserID");
		validator.stringValidate(product.getId(), "ProductID");
		product.setUserShoppingCart(user);
		productRepository.save(product);
	}
	
	public void removeFromCart(Product product) throws Exception {	
		validator.stringValidate(product.getId(), "ProductID");
		product.setUserShoppingCart(null);
		productRepository.save(product);
	}
	
	/* MOSTRAR TODOS LOS PRODUCTOS(LISTA) - MOSTRAR UN PRODUCTO(CLICK) - MOSTRAR PRODUCTOS POR FILTRO - ALTA/BAJA */
	
	/* MOSTRAR TODOS LOS PRODUCTOS(LISTA) - MOSTRAR UN PRODUCTO(CLICK) - MOSTRAR PRODUCTOS POR FILTRO - ALTA/BAJA */
	//READ
	/**
	 * Lists all products.
	 */
	public List<Product> getAll() {
            try {
                return productRepository.findAll();
            } catch (Exception e) {            
                System.err.println("The method getAll from ProductService has failed and has throw the next message: " + e.getMessage());
            }
            return null;
	}
	
	/**
	 * Get product by id.
	 */
	public Product getById(String id) {
            try {
                return productRepository.getById(id);
            } catch (Exception e) {
                System.err.println("The method getById from ProductService has failed and has throw the "
                        + "next message: " + e.getMessage());
            }
            return null;
	}
	
	/**
	 * Get products by name.
	 */
	public List<Product> getByName(String name) {
		return productRepository.getByName(name);
	} 
	/**
	 * Seller filter.
	 */
	public List<Product> getProductsBySellerId(String id){
            try {
                return productRepository.getProductsBySellerId(id);
            } catch (Exception e) {
                System.err.println("The method getProductsBySellerId from ProductService has failed and "
                        + "has throw the next message: " + e.getMessage());
            }
            return null;
	}
	/*
	 * Frank/David háganlo ustedes
	 */
	public List<Product> getShoppingCartProductsByUser(MyUser user) throws Exception{
		
		return productRepository.getShoppingCartByUserShoppingCart( user );
	}
	/**
	 * Category filter.
	 */
	public List<Product> getProductsByCategory(Categories category){
        try {
            return productRepository.getProductsByCategory(category.toString());
        } catch (Exception e) {
            System.err.println("The method getProductsByCategory from ProductService has failed and "
                    + "has throw the next message: " + e.getMessage());
        }
        return null;

//	public List<Product> getProductsByCategory(Categories category){
//        try {
//            return productRepository.getProductsByCategory(category);
//        } catch (Exception e) {
//            System.err.println("The method getProductsByCategory from ProductService has failed and "
//                    + "has throw the next message: " + e.getMessage());
//        }
//        return null;
//}
	
	public void productStatus(String id) throws Exception{
		Product product = productRepository.getById(id); 
		product.setEnabled(!product.getEnabled());
		productRepository.save(product);
		
	}
	//DELETE
	/*
	 * Deletes the product in database.
	 */
	public void deleteProduct(String id) throws Exception{
		try{
			productRepository.deleteById(id);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public Double devolverTotal(MyUser user) {
		List<Product> carrito = user.getShoppingCart();
		Double compraTotal = 0.0;
		for (Product product : carrito) {
			compraTotal += product.getPrice();
		}
		return compraTotal;
	}

	

	/* TODO BOOLEANO PARA VER SI EL USUARIO ES ADMIN PARA ELIMINAR
	public void deleteProduct(String id) throws Exception {
		
	}
	*/
	
	/* TODO METODO PARA VENDER COMBOS - PUEDE SER QUE EL FRONT HAGA ESTA PARTE(EL COMBO EN EL CARRITO DE COMPRAS)*/
}
