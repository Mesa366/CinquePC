package com.cinque.pc.Controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cinque.pc.Entities.Product;
import com.cinque.pc.Services.ProductService;
import org.springframework.web.multipart.MultipartFile;

@Controller
/* TODO revisar mapping permalink Martin */
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	MyUserService myUserService;

	@GetMapping("/{id}")
	public String product(@PathVariable String id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "product-single";
	}

	@GetMapping("/form")
	public String form() {
		return "product-form";
	}

	@PostMapping("/form")
	public String productRegister(String name, Double price, Integer stock, MultipartFile photo,String userId) throws Exception{
		MyUser user = myUserService.getById(userId);
		productService.createProduct(name,price,user, new Date(),stock);
		return "redirect:../";
	}
	@GetMapping("/catalog")
	public String catalog(Model model) {
		List<Product> catalog = productService.getAll();
		model.addAttribute("products", catalog);
		return "catalog";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable String id,Model model){
		Product product = productService.getById(id);
		model.addAttribute("product",product);
		return "product-form";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable String id,String name, Double price, Integer stock, MultipartFile photo) throws Exception{
		productService.editProduct(id,name,price,stock);
		return "product-single";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) throws Exception{
		productService.productStatus(id);
		return "redirect:../";
	}
}
