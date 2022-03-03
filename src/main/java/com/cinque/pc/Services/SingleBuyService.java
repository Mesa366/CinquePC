package com.cinque.pc.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinque.pc.Entities.SingleBuy;
import com.cinque.pc.Repositories.SingleBuyRepository;

@Service
public class SingleBuyService {

	@Autowired
	private Validator validator;
	
	@Autowired
	private SingleBuyRepository singleBuyRepository;
	
	@Autowired
	private MyUserService userService;
	
	@Autowired
	private ProductService productService;
	
	//CREATE
	public SingleBuy createShoppingCart(String userId, Integer quantity, String productId) throws Exception {
		
		validator.stringValidate(userId, "UserId");
		validator.stringValidate(productId, "ProductId");
		validator.integerValidate(quantity, "Quantity");
		
		SingleBuy singleBuy = new SingleBuy();
		singleBuy.setQuantity(quantity);
		singleBuy.setUserShoppingCart( userService.getById(userId) );
		singleBuy.setProduct( productService.getById(productId) );
		
		return singleBuyRepository.save(singleBuy);
	}
	
	//UPDATE
	public SingleBuy updateShoppingCart(String id, String userId, Integer quantity, String productId) throws Exception {
		validator.stringValidate(id, "SingleBuyId");
		validator.stringValidate(userId, "UserId");
		validator.stringValidate(productId, "ProductId");
		validator.integerValidate(quantity, "Quantity");
		
		SingleBuy singleBuy = singleBuyRepository.getById(id);
		singleBuy.setQuantity(quantity);
		singleBuy.setUserShoppingCart( userService.getById(userId) );
		singleBuy.setProduct( productService.getById(productId) );
		
		return singleBuyRepository.save(singleBuy);
	}
	
	//GETTERS
	
	//DELETE
	public void delete(String id) throws Exception {
		validator.stringValidate(id, "Id");
		singleBuyRepository.delete( singleBuyRepository.getById(id) );
	}
}
