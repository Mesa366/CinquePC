package com.cinque.pc.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Repositories.MyUserRepository;

/**
 * 
 * @author Lamberti
 *
 */
@Service
public class MyUserService {

	@Autowired
	private Validator validator;
	
	/* TODO Tenemos que hacer repositorio y servicio de imagen
	@Autowired
	private Image;
	*/
	/* TODO Tenemos que hacer el uso de MyUserDetailsService
	 * 
	 */
	@Autowired
	private MyUserRepository userRepo;

	//CREATE
	/**
	 * Creates user 
	 */
	public void createUser(String name, String password, String email, Integer dni, Integer phone, Date birthday, MultipartFile picture) throws Exception {
		validator.stringValidate(name, "Name");
		validator.stringValidate(password, "Password");
		validator.stringValidate(email, "Email");
		validator.integerValidate(dni, "DNI");
		validator.integerValidate(phone, "Phone");
		validator.dateValidate(birthday, "Birthday");
				
		MyUser user = new MyUser();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setDni(dni);
		user.setPhone(phone);
		user.setBirthday(birthday);
		
		userRepo.save(user);		
	}
	
	//UPDATE
	/**
	 * Updates user
	 */
	public void updateUser(String id, String name, String password, String email, Integer dni, Integer phone, Date birthday, MultipartFile picture) throws Exception {
		validator.stringValidate(id, "ID");
		validator.stringValidate(name, "Name");
		validator.stringValidate(password, "Password");
		validator.stringValidate(email, "Email");
		validator.integerValidate(dni, "DNI");
		validator.integerValidate(phone, "Phone");
		validator.dateValidate(birthday, "Birthday");
				
		MyUser user = userRepo.getById(id);
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setDni(dni);
		user.setPhone(phone);
		user.setBirthday(birthday);
		
		userRepo.save(user);		
	}
	
	//READ
	/**
	 * Get user by id
	 */
	public MyUser getById(String id) {
		return userRepo.getById(id);
	}
	
	//READ
	/**
	 * Lists all users
	 */
	public List<MyUser> getAll() {
		return userRepo.findAll();
	}		

	//DELETE
	/**
	 * Deletes user
	 */
	public void deleteUser(String id) {
		MyUser user = userRepo.getById(id);
		userRepo.delete(user);
	}
	
	
}
