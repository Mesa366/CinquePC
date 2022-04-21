package com.cinque.pc.Controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Entities.SingleBuy;
import com.cinque.pc.Enums.Categories;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.ProductService;
import com.cinque.pc.Services.SingleBuyService;
import com.cinque.pc.Services.Validator;

@Controller
/* TODO revisar mapping permalink Martin */
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	MyUserService myUserService;
	@Autowired
	SingleBuyService sbService;

	@GetMapping("/{id}")
	public String product(@PathVariable String id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		model.addAttribute("title", product.getName());
		return "product.html";
	}

	@GetMapping("/form")
	public String form(Model model) {

		model.addAttribute("product", new Product() );
		model.addAttribute("categories", Categories.values());
		model.addAttribute("title", "Creating product");
		return "test-productRegister";
	}

	@PostMapping("/form")
	public String createProduct(String name, Double price, Integer stock,@RequestParam("userId")String userId, Categories category, MultipartFile photo) throws Exception{
		
		MyUser user = myUserService.getById(userId);
		
		productService.createProduct(name, price, user, stock, category,photo);
		return "redirect:/product/catalog";
	}
	
	@GetMapping("/catalog")
	public String catalog(Model model, Principal principal) throws Exception {
		List<Product> catalog = productService.getAll();

		model.addAttribute("title", "Products Catalog");
		model.addAttribute("products", catalog);
		if(principal != null) {
			MyUser user = myUserService.getByEmail( principal.getName() );
			
			Double compraTotal = productService.devolverTotal(user);
			model.addAttribute("compraTotal", compraTotal);

			model.addAttribute("wishList", productService.getWishListProductsByUserWishList(user));
			
			model.addAttribute("shoppingCart", sbService.getByUserShoppingCart(user) );
			
			model.addAttribute("registered", true);
		}else {
			model.addAttribute("registered", false);			
		}

		return "testCatalog";
	}

	@PostMapping("/catalog")
	public String catalogPost(Model model, Principal principal, String word) throws Exception {
		List<Product> catalog = productService.getByName(word);

		model.addAttribute("products", catalog);
		if(principal != null) {
			MyUser user = myUserService.getByEmail( principal.getName() );

			Double compraTotal = productService.devolverTotal(user);
			model.addAttribute("compraTotal", compraTotal);

			model.addAttribute("wishList", productService.getWishListProductsByUserWishList(user));

			model.addAttribute("shoppingCart", sbService.getByUserShoppingCart(user) );

			model.addAttribute("registered", true);
		}else {
			model.addAttribute("registered", false);
		}

		return "testCatalog";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable String id,Model model){
		Product product = productService.getById(id);
		model.addAttribute("product",product);
		model.addAttribute("categories", Categories.values());
		model.addAttribute("title", "Update Product");
		return "test-productUpdate";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable String id,String name, Double price, Integer stock, Categories category) throws Exception{
		productService.editProduct(id,name,price,stock, category);
		return "redirect:../catalog";
	}
	
	@GetMapping("/addToWishList/{id}")
	public String addToWishList(@PathVariable String id, Principal principal) throws Exception{
		MyUser user = myUserService.getByEmail( principal.getName() );
		Product product = productService.getById(id);
		productService.addToWishList(user, product);
		return "redirect:/product/catalog";
	}
	
	@GetMapping("/removeFromWishList/{id}")
	public String removeFromWishList(@PathVariable String id) throws Exception{
		Product product = productService.getById(id);		
		productService.removeFromWishList(product);
		return "redirect:/product/catalog";
	}
	
	@GetMapping("/buyCart")
	public String buyCart(Principal principal) {
		
		return "redirect:/product/catalog";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) throws Exception{
		productService.productStatus(id);
		return "redirect:../";
	}
}
