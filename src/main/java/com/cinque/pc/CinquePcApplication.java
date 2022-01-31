package com.cinque.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cinque.pc.Services.MyUserService;

@SpringBootApplication
public class CinquePcApplication {
	
	@Autowired
	MyUserService myUserService;

	public static void main(String[] args) {
		SpringApplication.run(CinquePcApplication.class, args);
	}
	
	/**
	 * Indica a la configuración de Spring Security cuál es el servicio utilizado para autenticar al usuario. (Chequear esta data)
	 * @param auth
	 */
	/*
	@Autowired
	public void configuraGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myUserService).passwordEncoder(new BCryptPasswordEncoder());
	}
	*/

}
