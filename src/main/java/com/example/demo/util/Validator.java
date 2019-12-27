package com.example.demo.util;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.example.demo.model.Person;


@Component
public class Validator {
	
	public boolean idNull(UUID id) {
		return !ObjectUtils.isEmpty(id);
	}
	
	public boolean nameNull(String name) {
		return !ObjectUtils.isEmpty(name);		
	}
	
	
	public boolean personNull(Person person) {		
		return ObjectUtils.isEmpty(person);
		
	}
	
}
