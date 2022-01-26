package com.cinque.pc.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Lamberti
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
	public void dateValidate(Date content, String name) throws Exception {
		
		if(content == null) {
			throw new Exception(name + " can´t be null");
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
	
}
