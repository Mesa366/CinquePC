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
		
                Image profilePicture = imageService.saveImage(picture);
                user.setProfilePicture(profilePicture);
                
		userRepo.save(user);		
	}
	
        //ELIMINAR?
	public void createUser(String name, String password1, String password2, String email, String dni, String phone, 
            MultipartFile picture) throws Exception {
	
	validator.stringValidate(name, "Name");
	validator.passwordValidate(password1, password2);
	validator.stringValidate(email, "Email");
	validator.stringValidate(dni, "DNI");
	validator.stringValidate(phone, "Phone");
	
			
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
	//user.setBirthday(birthday);
	
            Image profilePicture = imageService.saveImage(picture);
            user.setProfilePicture(profilePicture);
            
	userRepo.save(user);		
}
	
	//UPDATE
	/**
	 * Updates user
	 */
	public void updateUser(String id, String name, String password1, String password2, String email, String dni, 
                String phone, LocalDate birthday, MultipartFile picture) throws Exception {
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
