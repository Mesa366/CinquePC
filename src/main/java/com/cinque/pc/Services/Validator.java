package com.cinque.pc.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * This service can validate if the parameters are null or empty and throw an exception if they are null
 */

@Service
public class Validator {
	
	/**
	 * 
	 * @param content It's the "content" original variable, in this case it's a String
	 * @param name It's the "content" original name or the name that we want to show in the case of a thrown exception
	 * @throws Exception Throws an exception in a null case
	 */
	public void stringValidate(String content, String name) throws Exception {
		if(content == null || content.isEmpty()) {
			throw new Exception(name + " can´t be null");
		}
	}
	/**
	 * 
 	 * @param content It's the "content" original variable, in this case it's a Date 
	 * @param name It's the "content" original name or the name that we want to show in the case of a thrown exception
	 * @throws Exception Throws an exception in a null case
	 */
	public void dateValidate(LocalDate content, String name) throws Exception {
		if(content == null) {
			throw new Exception(name + " can´t be null + " + content);
		}
	}
	
	/**
	 * 
	 * @param content It's the "content" original variable, in this case it's a Double
	 * @param name It's the "content" original name or the name that we want to show in the case of a thrown exception
	 * @throws Exception Throws an exception in a null case
	 */
	public void doubleValidate(Double content, String name) throws Exception {
		if(content == null || content <= 0) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	/**
	 * 
	 * @param content It's the "content" original variable, in this case it's an Integer
	 * @param name It's the "content" original name or the name that we want to show in the case of a thrown exception
	 * @throws Exception Throws an exception in a null case
	 */
	public void integerValidate(Integer content, String name) throws Exception {
		if(content == null || content <= 0) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	/**
	 * 
	 * @param content It's the "content" original variable, in this case it's a String List
	 * @param name It's the "content" original name or the name that we want to show in the case of a thrown exception
	 * @throws Exception Throws an exception in a null case
	 */
	public void listValidate(List<String> content, String name) throws Exception {
		if(content == null || content.isEmpty()) {
			throw new Exception(name + " can´t be null");
		}
	}

	/**
	 * Method to verify if the passwords are the same.
	 * @param password1 It's the "password" original variable, in this case it's a String
	 * @param password2 It's the "password" to verify
	 * @throws Exception Throws an exception if the passwords aren't equals
	 */
	public void passwordValidate(String password1, String password2) throws Exception {
		stringValidate(password1,"password");
		stringValidate(password2,"password");
		if(!password1.equals(password2)){
			throw new Exception("passwords don't match");
		}
	}
	
	/**
	 * Method to verify if the user has the amount of money to withdrawal it
	 * @param withdrawal It's the quantity of money that the user wants to withdraw 
	 * @param userWallet It's the actual money that the user has.  
	 */
	public void withdrawalValidate(Integer withdrawal, Integer userWallet) throws Exception {
		if( userWallet < withdrawal ){ // If the user has less money that the quantity that he wants to withdraw, throws exception
			throw new Exception("Not enough money");
		}
	}
	
	//TODO Validar mayoría de edad.
	
	
}
