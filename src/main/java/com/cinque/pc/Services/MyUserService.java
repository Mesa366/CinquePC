package com.cinque.pc.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Repositories.MyUserRepository;

@Service
public class MyUserService implements UserDetailsService{

	@Autowired
	private Validator validator;

	@Autowired
	private MyUserRepository userRepo;
        
    @Autowired
    private ImageService imageService;

	//CREATE
	/**
	 * Creates user 
        * @param name
        * @param password
        * @param email
        * @param dni
        * @param phone
        * @param birthday
        * @param picture
        * @throws java.lang.Exception
	 */
	public void createUser(String name, String password1, String password2, String email, String dni, String phone, 
                LocalDate birthday, MultipartFile picture) throws Exception {		
		validator.stringValidate(name, "Name");
		validator.passwordValidate(password1, password2);
		validator.stringValidate(email, "Email");
		validator.stringValidate(dni, "DNI");
		validator.stringValidate(phone, "Phone");
		validator.dateValidate(birthday, "Birthday");
				
		MyUser user = new MyUser();
		user.setName(name);
		/**
		 * Password encryption
		 */
		String encPass = new BCryptPasswordEncoder().encode(password1);
		
		user.setPassword(encPass);

		user.setEmail(email);
		user.setDni(dni);
		user.setPhone(phone);
		user.setBirthday(birthday);
		/*
		 * We use our image service to convert MultipartFile into Image class
		 */
        Image profilePicture = imageService.saveImage(picture);
        user.setProfilePicture(profilePicture);
                
		userRepo.save(user);		
	}
	
	//UPDATE
	/**
	 * Updates user data. 
	 * 
	 */
	public void updateUser(String id, String name, String password1, String password2, String email, String dni, 
                String phone, LocalDate birthday) throws Exception {
		validator.stringValidate(id, "ID");
		validator.stringValidate(name, "Name");
		validator.passwordValidate(password1, password2);
		validator.stringValidate(email, "Email");
		validator.stringValidate(dni, "DNI");
		validator.stringValidate(phone, "Phone");
		validator.dateValidate(birthday, "Birthday");
				
		MyUser user = userRepo.getById(id);
		
		String encPass = new BCryptPasswordEncoder().encode(password1);
		
		user.setName(name);
		
		user.setPassword(encPass);
		user.setEmail(email);
		user.setDni(dni);
		user.setPhone(phone);
		user.setBirthday(birthday);
		userRepo.save(user);		
	}
	//UPDATE PROFILE PICTURE
	/**
	 * Only updates user's profile picture. 
	 * @param picture It's the MultipartFile coming from the frontend.
	 * @param id It's the user's id. 
	 */
	public void updateProfilePicture(MultipartFile picture, String id) throws Exception {
		MyUser user = userRepo.getById(id);
		Image profilePicture = imageService.saveImage(picture);
        user.setProfilePicture(profilePicture);
        userRepo.save(user);
	}
	
	//READ
	/**
	 * Get user by id.
	 */
	public MyUser getById(String id) {
		return userRepo.getById(id);
	}
	/**
	 * Get user by email.
	 */
	public MyUser getByEmail(String email) {
		return userRepo.getByEmail(email).get();
	}
	
	/**
	 * Lists all users.
	 */
	public List<MyUser> getAll() {
		return userRepo.findAll();
	}		

	//DELETE
	/**
	 * Deletes user.
	 */
	public void deleteUser(String id) {
		MyUser user = userRepo.getById(id);
		userRepo.delete(user);
	}
	

	/**
	 * @Author Franco Lamberti
	 * Method to withdraw money from an user
	 * @param withdrawal It's the quantity of money that the user wants to withdraw 
	 * @param user It's the user that wants to withdraw money  
	 * @throws Exception 
	 */
	public void withdrawMoney(Integer withdrawal,  MyUser user) throws Exception {
		validator.withdrawalValidate(withdrawal, user.getWallet());
		user.setWallet( user.getWallet() - withdrawal );
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<MyUser> opt = userRepo.getByEmail(email);
		if (opt.isPresent()) {
			MyUser myUser = opt.get();
			
			//Creación de permisos
			List<GrantedAuthority> authorities = new ArrayList();
			
			GrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_REGISTERED_USER");
			authorities.add(authority1);
			GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_ADMIN_USER");
			authorities.add(authority2);
			
			//Sesión de usuario
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			
			session.setAttribute("usersession", myUser);
			
			//Usuario logeado, con sus respectivos permisos.
			User user = new User(myUser.getEmail(), myUser.getPassword(), authorities);
			return user;
		}else{
		return null;
		}
		}

	
	
}
