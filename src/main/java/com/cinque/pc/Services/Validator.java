package com.cinque.pc.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Validator {
	
	public void stringValidate(String content, String name) throws Exception {
		
		if(content == null || content.isEmpty()) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	public void dateValidate(Date content, String name) throws Exception {
		
		if(content == null) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	public void doubleValidate(Double content, String name) throws Exception {
		
		if(content == null || content <= 0) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	public void integerValidate(Integer content, String name) throws Exception {
		
		if(content == null || content <= 0) {
			throw new Exception(name + " can´t be null");
		}
	}
	
	public void listValidate(List<String> content, String name) throws Exception {
		
		if(content == null || content.isEmpty()) {
			throw new Exception(name + " can´t be null");
		}
	}
	
}
