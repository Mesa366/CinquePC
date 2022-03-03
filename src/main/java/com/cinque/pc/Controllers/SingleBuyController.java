package com.cinque.pc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinque.pc.Services.SingleBuyService;

@Controller
@RequestMapping("/sb")
public class SingleBuyController {

	@Autowired
	private SingleBuyService singleBuyService;
	
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
	@GetMapping("/delete")
	public String deleteSingleBuy(String singleBuyId) throws Exception{
		singleBuyService.delete(singleBuyId);
		return "redirect:/product/catalog";
	}
}
