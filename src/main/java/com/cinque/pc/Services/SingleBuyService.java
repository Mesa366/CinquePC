package com.cinque.pc.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
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
	public SingleBuy getById(String id) throws Exception {
		validator.stringValidate(id, "Id");
		return singleBuyRepository.getById(id);
	}
	
	public List<SingleBuy> getAll() {
		return singleBuyRepository.findAll();
	}
	
	public List<SingleBuy> getByUserShoppingCart(MyUser user) {
		return singleBuyRepository.getShoppingCartByUserShoppingCart(user);
	}
	
	public List<SingleBuy> getByUserShoppingHistory(MyUser user) {
		return singleBuyRepository.getShoppingHistoryByUserShoppingHistory(user);
	}
	
	public List<SingleBuy> getByUserSellingProducts(MyUser user) {
		return singleBuyRepository.getSellingProductsByUserSellingProducts(user);
	}
	
	//DELETE
	public void delete(String id) throws Exception {
		validator.stringValidate(id, "Id");
		singleBuyRepository.delete( singleBuyRepository.getById(id) );
	}
	
	//BuyAll
	public void buyAllShoppingCart(MyUser user) throws Exception {
		validator.stringValidate(user.getId(), "UserId");//Valido que el usuario exista
		Double total = productService.devolverTotal(user); //Obtengo el total
		validator.withdrawalValidate(total, user.getWallet() ); //Valido que el usuario pueda hacer la transaccion
		List<SingleBuy> shoppingCart = user.getShoppingCart();//Obtengo el carrito
		
		userService.withdrawMoney(total, user); //Retiro el dinero de el comprador
		System.out.println("Usuario comprador:" + user.getEmail() + " tiene $" + user.getWallet());

		List<SingleBuy> historial = user.getShoppingHistory(); 
		
		for(int i = 0; i < shoppingCart.size(); i++) {//Recorro cada producto
			SingleBuy singleBuy = shoppingCart.get(i);
			Product product = shoppingCart.get(i).getProduct();
			
			MyUser seller = product.getSeller(); //Obtengo el vendedor del producto actual
			Double montoActual = product.getPrice() * singleBuy.getQuantity(); //montoActual es igual a el precio del producto actual multiplicado la cantidad
			userService.depositMoney(montoActual, seller);//Le deposito el dinero debido a el vendedor actual
			userService.save(seller);//guardo los cambios del vendedor			
									
			product.setStock(  product.getStock() - singleBuy.getQuantity() ); //Le saco el stock que compré
			singleBuy.setUserShoppingHistory(user);//guardo al usuario actual como comprador de este producto, o sea que con esto va a su historial
			historial.add(singleBuy);
		}
		System.out.println("Usuario comprador al comprar todo:" + user.getEmail() + " tiene $" + user.getWallet());
		user.setShoppingHistory(historial);
		user.getShoppingCart().clear();//Vacío el carrito
		
		userService.save(user);
		
	}
}
