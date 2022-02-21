package com.cinque.pc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Services.MyUserService;
import com.cinque.pc.Services.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photo")
public class ImageController {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/user")
	public ResponseEntity<byte[]> getUserImage(@RequestParam String id){
		try {
			MyUser user = myUserService.getById(id);
			byte[] image = user.getProfilePicture().getContent();	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); //TODO revisar si hay que cambiar o no los tipos MediaType
			
			return new ResponseEntity<>(image, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<byte[]> getProductImage(@PathVariable String id){
		try {
			Product product = productService.getById(id);
			byte[] image = product.getPhoto().getContent();	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); //TODO revisar si hay que cambiar o no los tipos MediaType
			
			return new ResponseEntity<>(image, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
