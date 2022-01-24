package com.cinque.pc.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Validator validator;
	
	public void createProduct (String name, Double price, MyUser seller, Date sellingDate, List<String> categories ,Integer stock) throws Exception {
		
		validator.stringValidate(name, "Name");
		validator.dateValidate(sellingDate, "Selling Date");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA */
		validator.listValidate(categories, "Categories");
		
		Product product = new Product(); 
		
		product.setName(name);
		product.setPrice(price);
		product.setSeller(seller);
		product.setSellingDate(sellingDate);
		product.setCategories(categories);
		product.setStock(stock);
		
		productRepository.save(product);
		
	}
	
	public void editProduct(String id, String name, Double price, List<String> categories ,Integer stock) throws Exception{
		
		validator.stringValidate(name, "Name");
		validator.doubleValidate(price, "Price");
		validator.integerValidate(stock, "Stock");
		/* TODO UTILIZAR LIST<OBJECT> COMO LISTA GENERICA */
		validator.listValidate(categories, "Categories");
		
		Product product = productRepository.getById(id); 
		
		product.setName(name);
		product.setPrice(price);
		product.setCategories(categories);
		product.setStock(stock);
		
		productRepository.save(product);
		
	}
	
}
