package com.cinque.pc.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.ProductService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class MyUserController {
	
	/* TODO preauthorized */
	
	
	@Autowired 
	private MyUserService myUserService;
	
	@Autowired
	private ProductService productService;
        
	
	@GetMapping("/{id}")
	public String profile(@PathVariable String id, Model model) {
		
		model.addAttribute("user", myUserService.getById(id));		
		return "profile";
	}
	
	@GetMapping("/{id}/seller-list")
	public String sellerList(@PathVariable String id, Model model) {
		
		model.addAttribute("sellerList", myUserService.getById(id).getSellingProducts());
		
		return "catalog";
	}
	
	
	@GetMapping("/{id}/wish-list")
	public String wishList(@PathVariable String id, Model model) {
		
		model.addAttribute("wishList", myUserService.getById(id).getWishList());
		
		return "catalog";
	}

	//6.- Ver historial de compra 
	//Lo pusimos como atributo Atte: Bombón, bellota y burbuja
	//TODO mostrar historial de compra
	/*@GetMapping("/{id}/buying-history")
	public String buyingHistory(@PathVariable String id, Model model) {
		model.addAttribute("history", productService.getProductsByBuyerId(id));
		return "catalog";
	}
	*/
	@GetMapping("/{id}/selling-history")
	public String sellingHistory(@PathVariable String id, Model model) {
		model.addAttribute("history", productService.getProductsBySellerId(id));
		return "catalog";
	}
	
}
