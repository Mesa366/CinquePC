package com.cinque.pc.Controllers;

import java.sql.Date;

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
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	MyUserService myUserService;

	
	@GetMapping("/form/{state}")
	public String form(@PathVariable String state, Model model) {
		if(!(state.equals("register")) || !(state.equals("login")) || !(state.equals("update"))) {
			return "redirect:../";
		}
		if (state.equals("login")) {
			model.addAttribute("state", "login");
		}
		if (state.equals("register")) {
			model.addAttribute("state", "register");
		}
		if (state.equals("update")) {
			model.addAttribute("state", "update");
		}		
		return"index";
	}

	/**
	USER UPDATE
	*/

	//TODO method for changing password securely
	//TODO can the user simply change his/her email?
	@PostMapping("/update/{id}")
	public String updateUserRedirect(@PathVariable String id,Image profilePicture,
	String name, String password, String email, Integer dni, Integer phone,
	Date birthday) {
		

		return "redirect:/{id}";
		
	}

		
	//USER REGISTER
	@PostMapping("/register/{id}")
	public String registerUserRedirect(@PathVariable String id,Image profilePicture,
	String name, String password, String email, Integer dni, Integer phone,
	Date birthday) {
		
		return "redirect:/{id}";
		
	}



}
