package com.cinque.pc.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Enums.Categories;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.ProductService;

@Controller
/* TODO revisar mapping permalink Martin */
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	MyUserService myUserService;
	@Autowired
	Categories category;

	@GetMapping("/{id}")
	public String product(@PathVariable String id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "product-single";
	}

	@GetMapping("/form")
	public String form(ModelMap model) {

//		List<String> categories = new ArrayList<String>();
//		for (Categories categorie : category.values()) {
//			categories.add(categorie.toString());
//		}
		
		model.put("categories", category);

		return "product-form";
	}

	@PostMapping("/form")
	public String productRegister(String name, Double price, Integer stock, MultipartFile photo,String userId, String category) throws Exception{
		MyUser user = myUserService.getById(userId);
		productService.createProduct(name,price,user, null,stock, category);
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
	public String updateProduct(@PathVariable String id,String name, Double price, Integer stock, MultipartFile photo, String category) throws Exception{
		productService.editProduct(id,name,price,stock, category);
		return "product-single";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) throws Exception{
		productService.productStatus(id);
		return "redirect:../";
	}
}
