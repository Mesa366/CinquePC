package com.cinque.pc.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Enums.Categories;
import com.cinque.pc.Repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private MyUserService userService;

	public void createProduct (String name, Double price, MyUser seller, Integer stock, Categories category) throws Exception {
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA (Why??)*/
		
		System.out.println("Entré a create product");
		
		Product product = new Product(); 
		
		product.setName(name);
		product.setPrice(price);
		product.setSeller(seller);
		//product.setSellingDate(sellingDate);
		product.setStock(stock);
				System.out.println("seteando");
		seller.getSellingProducts().add(product);
		product.setCategory(category);
		productRepository.save(product);
		
	}
	
	public void editProduct(String id, String name, Double price,Integer stock, Categories category) throws Exception{
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA
		*   TODO AGREGAR  List<String> categories A LOS PARAMETROS */
		
		Product product = productRepository.getById(id); 
		
		product.setName(name);
		product.setPrice(price);
		product.setCategory(category);
		product.setStock(stock);
		
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
	
	public List<Product> getAll() {
            try {
                return productRepository.findAll();
            } catch (Exception e) {            
                System.err.println("The method getAll from ProductService has failed and has throw the next message: " + e.getMessage());
            }
            return null;
	}
	
        //TODO unir metodos get y find
	public Product getById(String id) {
            try {
                return productRepository.getById(id);
            } catch (Exception e) {
                System.err.println("The method getById from ProductService has failed and has throw the "
                        + "next message: " + e.getMessage());
            }
            return null;
	}
	
	//TODO convertir a findByName (para un buscador)
	/* TODO 
	 *  public List<Product> getByAtt(){
		return ;
	} */
	
	
//	public Optional<Product> findById(String id) {
//            try {
//                return productRepository.findById(id);
//            } catch (Exception e) {
//                System.err.println("The method findById from ProductService has failed and has throw the "
//                        + "next message: " + e.getMessage());
//            }
//            return null;
//	}
	
	public List<Product> getProductsBySellerId(String id){
            try {
                return productRepository.getProductsBySellerId(id);
            } catch (Exception e) {
                System.err.println("The method getProductsBySellerId from ProductService has failed and "
                        + "has throw the next message: " + e.getMessage());
            }
            return null;
	}
	
	public List<Product> getShoppingCartProductsByUser(MyUser user) throws Exception{
		
		return productRepository.getShoppingCartByUserShoppingCart( user );
	}

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
