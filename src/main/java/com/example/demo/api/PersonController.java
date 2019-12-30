package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.model.PersonResponse;
import com.example.demo.service.PersonService;
import com.example.demo.util.Validator;

@RequestMapping("api/v1/person/")
@RestController
public class PersonController {
	


	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping("create")
	public PersonResponse addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}
	
	@GetMapping("obtainAll")
	public PersonResponse getAllPeople(){	
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
