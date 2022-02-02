package com.cinque.pc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

	
	@GetMapping("/form/{state}")
	public String form(@PathVariable String state, Model model) {
		if(!(state.equals("register")) || !(state.equals("login"))) {
			return "redirect:../";
		}
		if (state.equals("login")) {
			model.addAttribute("state", "login");
		}
		if (state.equals("register")) {
			model.addAttribute("state", "register");
		}
		return"index";
	}

}
