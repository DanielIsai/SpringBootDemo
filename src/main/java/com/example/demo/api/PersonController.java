package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.model.PersonResponse;
import com.example.demo.service.PersonService;
import com.example.demo.util.Validator;

@RequestMapping("api/v1/person/")
@RestController
public class PersonController {
	@Value("${message1}")
	private String message1;

	private final PersonService personService;
	private final Validator validator;

	public PersonController(PersonService personService, Validator validator) {
		this.personService = personService;
		this.validator = validator;
		
	}
	
	
	@PostMapping("create")
	public PersonResponse addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}
	
	@GetMapping("obtainAll")
	public PersonResponse getAllPeople(){	
		System.out.println(message1);
		return personService.getAllPeople();
	}
	
	
	@GetMapping("obtainById")	
	public PersonResponse getPersonById(@RequestBody Person person) {
		 return personService.getPersonById(person);
	}
	

	@DeleteMapping("deleteById")
	public PersonResponse deletePersonById(@RequestBody Person person) {		
		return personService.deletePerson(person);
	}
	
	
	
	@PutMapping("updateById")
	public PersonResponse updatePerson(@RequestBody Person personToUpdate) {
		return personService.updatePerson(personToUpdate);
	}
	
	
	

}
