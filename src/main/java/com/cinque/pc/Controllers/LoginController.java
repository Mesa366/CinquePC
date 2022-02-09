package com.cinque.pc.Controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Services.MyUserService;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	MyUserService myUserService;

	@GetMapping("/form/{state}")
	public String showForm(@PathVariable("state") String state, Model model) {

		if (state.equals("login")) {
			model.addAttribute("state", "login");
		} else if (state.equals("register")) {
			model.addAttribute("state", "register");
			model.addAttribute("myUser", new MyUser());

		} else {
			return "redirect:../";
		}

		return "layout/user";

	}

	@GetMapping("/form/{state}/{id}")
	public String form(@PathVariable("state") String state, Model model, @PathVariable("id") String id) {

		/* TODO cambiar type por password en el html user */

		if ((state.equals("delete"))) {
			myUserService.deleteUser(id);
			return "redirect:../";
		} else if (state.equals("update")) {
			model.addAttribute("state", "update");
			model.addAttribute("myUser", myUserService.getById(id));
		} else {
			return "redirect:../";
		}
		return "layout/user";
	}

	// TODO TEST (ESTE FUNCA!)
//	@GetMapping("/form/{id}")
//	public String form1(Model model, @PathVariable("id") String id) {
//		
//		model.addAttribute("state", "update");
//		
//		model.addAttribute("myUser", myUserService.getById(id));
//		
//		return"layout/user";
//	}

	/**
	 * USER UPDATE
	 */

	// TODO method for changing password securely
	// TODO can the user simply change his/her email?
//	@PostMapping("/update/{id}")
//	public String updateUserRedirect(@PathVariable String id,Image profilePicture,
//	String name, String password, String email, Integer dni, Integer phone,
//	Date birthday) {
//		
//
//		return "redirect:/{id}";
//		
//	}

	// USER REGISTER
	@PostMapping("/register")
	public String registerUserRedirect(Image profilePicture, String name, String password, String email, Integer dni,
			Integer phone) throws Exception {

		myUserService.createUser(name, password, email, dni, phone, null, null);

		return "redirect:../";

	}

	// UPDATE REGISTER
	@PostMapping("/update/{id}")
	public String updateUserRedirect(@PathVariable String id, Image profilePicture, String name, String password,
			String email, Integer dni, Integer phone) throws Exception {

		myUserService.updateUser(id, name, password, email, dni, phone, null, null);

		return "redirect:../";

	}

	/*
	 * @PostMapping("/register")
	 * 
	 * public String registerUserRedirect(@RequestParam MyUser myUser) {
	 * 
	 * myUserService.createUser(myUser);
	 * 
	 * return "redirect:../"; }
	 */

}
