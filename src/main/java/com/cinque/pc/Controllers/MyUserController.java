package com.cinque.pc.Controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Services.MyUserService;

@Controller
@RequestMapping("/user")
public class MyUserController {
	
	/* TODO preauthorized */
	
	/*Controllers needed: 
	
	
	 * 6.- Ver historial de compra
	 * 7.- Wishlist
	 * 
	 * 	 */
	
	@Autowired 
	private MyUserService myUserService;
	
	@GetMapping("/{id}")
	public String profile(@PathVariable String id, Model model) {
		
		model.addAttribute("user", myUserService.getById(id));
		
		return "profile";
	}
	
	@GetMapping("/update/{id}")
	public String updateUser(@PathVariable String id, Model model) {
		
		model.addAttribute("user", myUserService.getById(id));
		
		return "userProfile";		
	}

	
	//TODO method for changing password securely
	//TODO can the user simply change his/her email?
	@PostMapping("/update/{id}")
	public String updateUserRedirect(@PathVariable String id,Image profilePicture,
	String name, String password, String email, Integer dni, Integer phone,
	Date birthday) {
		
		return "redirect:/{id}";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		myUserService.deleteUser(id);
		
		return "redirect:../";
	}
	
	
	@GetMapping("/{id}/sellerList")
	public String sellerList(@PathVariable String id, Model model) {
		
		model.addAttribute("sellerList", myUserService.getById(id).getSellingProduct());
		
		return "catalog";
	}
	
	

}
