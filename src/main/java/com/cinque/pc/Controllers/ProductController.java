package com.cinque.pc.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinque.pc.Entities.Product;
import com.cinque.pc.Services.ProductService;

@Controller
/* TODO revisar mapping permalink Martin */
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/{id}")
	public String product(@PathVariable String id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "product";
	}

	@GetMapping("/form")
	public String form(@RequestParam(required = false) String id, Model model) {
		if (id != null) {

			Optional<Product> opt = productService.findById(id);
			if (opt.isPresent()) {
				model.addAttribute("product", opt.get());
			} else {
				return "productForm"; //return "redirect:/";
			}

		} else {
			model.addAttribute("product", new Product());
		}
		return "productForm";
	}

	@GetMapping("/catalog")
	public String catalog(Model model) {
		List<Product> catalog = productService.getAll();
		model.addAttribute("products", catalog);
		return "catalog";
	}

	// @PostMapping("/")

}
