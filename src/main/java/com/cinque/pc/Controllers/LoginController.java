package com.cinque.pc.Controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			model.addAttribute("user", new MyUser());
			return "register";
		} else {
			return "redirect:/auth/form/login";
		}

		return "layout/user";

	}

	@GetMapping("/form/{state}/{id}")
	public String form(@PathVariable("state") String state, Model model, @PathVariable("id") String id) {

		if ((state.equals("delete"))) {
			myUserService.deleteUser(id);
			return "redirect:/auth/form/login";
		} else if (state.equals("update")) {
			model.addAttribute("state", "update");
			model.addAttribute("myUser", myUserService.getById(id));
		} else {
			return "redirect:/auth/form/login";
		}
		return "layout/user";
	}

	// TODO method for changing password securely
	// TODO can the user simply change his/her email?

	// USER REGISTER

	@PostMapping("/register")
	public String registerUserRedirect(RedirectAttributes ra, String name, String password1, String password2, String email, String dni, String phone, MultipartFile photo, @RequestParam(name = "birthday", required = false) 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy") String birthday ) {
		System.out.println("Entre al postmapping de /auth/register");
		try {
			LocalDate localDate = LocalDate.parse(birthday);
			
			myUserService.createUser(name, password1, password2, email, dni, phone, localDate, photo);
            ra.addFlashAttribute("success", "User created successfully. Sign in now!"); // --> Esto añade una pequeña ventana cuando el usuario se crea satisfactoriamente
            return "redirect:/user/register";
		} catch (Exception e) {
                    ra.addFlashAttribute("error", e.getMessage());                    
		}
		return "redirect:/user/register";
	}
	
	
	
	
	// UPDATE USER
	@PostMapping("/update/{id}")
	public String updateUserRedirect(@PathVariable String id, Image profilePicture, String name, String password1, String password2,
			String email, String dni, String phone) throws Exception {
		System.out.println("NAME: "+name);
		System.out.println("CLAVE: "+password1);
		System.out.println("CLAVE2: "+password2);		
		System.out.println("MAIL: "+email);
		System.out.println("DNI: "+dni);
		System.out.println("NUM TEL: "+phone);
		myUserService.updateUser(id, name, password1, password2, email, dni, phone, null, null);

		return "redirect:/auth/form/update/" + id ;
	}

}
