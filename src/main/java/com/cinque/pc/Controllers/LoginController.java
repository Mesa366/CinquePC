package com.cinque.pc.Controllers;

import java.time.LocalDate;

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
			model.addAttribute("title", "Login page");
		} else if (state.equals("register")) {
			model.addAttribute("state", "register");
			model.addAttribute("user", new MyUser());
			model.addAttribute("title", "Register page");
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
		try {
			LocalDate localDate = LocalDate.parse(birthday);
			
			myUserService.createUser(name, password1, password2, email, dni, phone, localDate, photo);
            ra.addFlashAttribute("success", "User created successfully. Sign in now!"); // --> Esto añade una pequeña ventana cuando el usuario se crea satisfactoriamente
            return "redirect:../";
		} catch (Exception e) {
                    ra.addFlashAttribute("error", e.getMessage());                    
		}

		return "redirect:../auth/form/register";
	}

	// UPDATE USER DATA
	@PostMapping("/update/{id}")
	public String updateUserRedirect(@PathVariable String id,String name, String password1, String password2,
			String email, String dni, String phone, @RequestParam(name = "birthday", required = false) 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy") String birthday) throws Exception {
		LocalDate localDate = LocalDate.parse(birthday);
		myUserService.updateUser(id, name, password1, password2, email, dni, phone, localDate);
		return "redirect:/auth/form/update/" + id ;
	}
	// UPDATE USER PROFILE PICTURE
	//TODO Hacer GETMAPPING del modal.
	@PostMapping("/update/{id}/photo")
	public String updatePictureUserRedirect(@PathVariable String id, MultipartFile profilePicture) throws Exception {
		myUserService.updateProfilePicture(profilePicture, id);
		return "redirect:/auth/form/update/" + id ;
	}

}
