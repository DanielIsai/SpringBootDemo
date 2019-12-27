package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;


@Component
public class PersonResponse {
	
	
	private String code;
	private String message;	
	private List<Person> personResult;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Person> getPersonResult() {
		return personResult;
	}

	public void setPersonResult(List<Person> people) {
		this.personResult = people;
	}
	
	public void setPersonResponse(String code, String message, List<Person> personResult) {
		this.code = code;
		this.message = message;
		this.personResult = personResult;
	}


	
	

}
