package com.cinque.pc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Entities.SingleBuy;
import com.cinque.pc.Enums.Categories;
import com.cinque.pc.Repositories.ProductRepository;

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
        * @param stock
        * @param category
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
		product.setCategory(category);
		//Transform from MultipartFile to Image, so we can set it to the attribute
		Image photo = imageService.saveImage(picture);
		product.setPhoto(photo);
		productRepository.save(product);
		
	}
	
	public Product save(Product product) throws Exception{
		validator.stringValidate(product.getId(), "ProductId");
		return productRepository.save(product);
	}
	//UPDATE
	/**
	 * Updates product data
        * @param name
        * @param price
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
	

	/*
	 * Set the product status (enabled/disabled)
	 */
	public void productStatus(String id) throws Exception{
		Product product = productRepository.getById(id); 
		product.setEnabled(!product.getEnabled());
		productRepository.save(product);
		
	}
	//Wishlist
	public void addToWishList(MyUser user, Product product) throws Exception {
		validator.stringValidate(user.getId(), "UserID");
		validator.stringValidate(product.getId(), "ProductID");
		product.setUserWishList(user);
		productRepository.save(product);
	}

	public void removeFromWishList(Product product) throws Exception {	
		validator.stringValidate(product.getId(), "ProductID");
		product.setUserWishList(null);
		productRepository.save(product);
	}
	//Wishlist
	
	//ShoppingCart
	public void addToShoppingCart(MyUser user, Product product, Integer quantity) throws Exception {
		validator.stringValidate(user.getId(), "UserID");
		validator.stringValidate(product.getId(), "ProductID");
		validator.integerValidate(quantity, "Quantity");
		
		product.setUserShoppingCart(user);
		
		
		productRepository.save(product);
	}
	//ShoppingCart

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
		System.out.println("Word="+name + "Returned:"+ productRepository.getByName(name));
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

	public List<Product> getWishListProductsByUserWishList(MyUser user) throws Exception{
		
		return productRepository.getWishListProductsByUserWishList( user );
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
		List<SingleBuy> carrito = user.getShoppingCart();
		Double compraTotal = 0.0;
		for (SingleBuy singleBuy : carrito) {
			compraTotal += ( singleBuy.getProduct().getPrice() * singleBuy.getQuantity() );
		}
		return compraTotal;
	}

}
