package com.cinque.pc.Controllers;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			return "register";
		} else {
			return "redirect:../";
		}

		return "layout/user";

	}

	@GetMapping("/form/{state}/{id}")
	public String form(@PathVariable("state") String state, Model model, @PathVariable("id") String id) {

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

	// TODO method for changing password securely
	// TODO can the user simply change his/her email?

	// USER REGISTER

	@PostMapping("/register")
	public String registerUserRedirect(MyUser user, MultipartFile photo, RedirectAttributes ra, String password2) {
		try {
                    myUserService.createUser(user.getName(), user.getPassword(), password2, user.getEmail(), 
                          user.getDni(), user.getPhone(), user.getBirthday(), photo);
                    ra.addFlashAttribute("success", "User created successfully. Sign in now!");
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
