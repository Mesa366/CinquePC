package com.cinque.pc.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.SingleBuyService;

@Controller
@RequestMapping("/sb")
public class SingleBuyController {

	@Autowired
	private SingleBuyService singleBuyService;
	
	@Autowired
	private MyUserService userService;
	
	//SAVE
	@PostMapping("/create")
	public String createSingleBuy(String idUser, String idProduct, Integer quantity) throws Exception{
		singleBuyService.createShoppingCart(idUser, quantity, idProduct);
		return "redirect:/product/catalog";
	}
		
	//UPDATE
	@PostMapping("/update")
	public String updateSingleBuy(String singleBuyId,String idUser, String idProduct, Integer quantity) throws Exception{
		singleBuyService.updateShoppingCart(singleBuyId, idUser, quantity, idProduct);
		return "redirect:/product/catalog";
	}
	
	//DELETE
	@GetMapping("/delete/{id}")
	public String deleteSingleBuy(@PathVariable ("id") String id) throws Exception{
		singleBuyService.delete(id);
		return "redirect:/product/catalog";
	}
	
	@GetMapping("/buyAllShoppingCart")
	public String buyAllShoppingCart(Principal principal) throws Exception {
		MyUser user =  userService.getByEmail( principal.getName() );
		singleBuyService.buyAllShoppingCart(user);
		return "redirect:/product/catalog";
	}
}
