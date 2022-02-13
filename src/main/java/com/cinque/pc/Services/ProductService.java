package com.cinque.pc.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private Validator validator;

	public void createProduct (String name, Double price, Integer enabled, MyUser seller, Date sellingDate, Integer stock, MultipartFile photo) throws Exception {
		
		validator.stringValidate(name, "Name");
		validator.dateValidate(sellingDate, "Selling Date");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		validator.integerValidate(enabled, "Enabled");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA */
		
		
		Product product = new Product(); 
		
		product.setName(name);
		product.setPrice(price);
		product.setSeller(seller);
		product.setSellingDate(sellingDate);
		product.setStock(stock);
		Image productPhoto = imageService.saveImage(photo);
		product.setPhoto(productPhoto);
		
		if(enabled != null) {
			if(enabled == 1) {
				product.setEnabled(true);
			}
			if(enabled == 2) {
				product.setEnabled(false);
			}
		}
		
		productRepository.save(product);
		
	}
	
	public void editProduct(String id, String name, Double price, Integer enabled,Integer stock, MultipartFile photo) throws Exception{
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		validator.integerValidate(enabled, "Enabled");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA
		*   TODO AGREGAR  List<String> categories A LOS PARAMETROS */
//		validator.listValidate(categories, "Categories");
		
		Product product = productRepository.getById(id); 
		
		product.setName(name);
		product.setPrice(price);		
		product.setStock(stock);
		Image productPhoto = imageService.saveImage(photo);
		product.setPhoto(productPhoto);
		
		if(enabled != null) {
			if(enabled == 1) {
				product.setEnabled(true);
			}
			if(enabled == 2) {
				product.setEnabled(false);
			}
		}
		
		productRepository.save(product);
		
	}
	
	/* MOSTRAR TODOS LOS PRODUCTOS(LISTA) - MOSTRAR UN PRODUCTO(CLICK) - MOSTRAR PRODUCTOS POR FILTRO - ALTA/BAJA */
	
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product getById(String id) {
		return productRepository.getById(id);
	}
	
	public Optional<Product> findById(String id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getProductsBySellerId(String id){
		return productRepository.getProductsBySellerId(id);
	}
	
	public List<Product> getProductsByBuyerId(String id){
		return productRepository.getProductsByBuyerId(id);
	}
	
	
	/* TODO 
	 *  public List<Product> getByAtt(){
		return ;
	} */
	
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

	/* TODO BOOLEANO PARA VER SI EL USUARIO ES ADMIN PARA ELIMINAR
	public void deleteProduct(String id) throws Exception {
		
	}
	*/
	
	/* TODO METODO PARA VENDER COMBOS - PUEDE SER QUE EL FRONT HAGA ESTA PARTE(EL COMBO EN EL CARRITO DE COMPRAS)*/
}
