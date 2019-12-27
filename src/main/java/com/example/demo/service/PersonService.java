package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import com.example.demo.model.PersonResponse;
import com.example.demo.util.Validator;


@Service
public class PersonService {
	
	
	private final PersonDao personDao;
	private final Validator validator;
	
	@Autowired						
	public PersonService(@Qualifier("fakeDao") PersonDao personDao, Validator validator) {
		this.personDao = personDao;
		this.validator = validator;
	}

	
	
	public PersonResponse addPerson(Person person) {
			
		PersonResponse personRes = new PersonResponse();	
		List<Person> people = new ArrayList<Person>();
		
		if(validator.nameNull(person.getName())) {		
			
			personDao.insertPerson(person);
			people.add(person);
			
			personRes.setCode("200");			
			personRes.setMessage("Person added");			
			personRes.setPersonResult(people);
			
			return personRes;
		}
		
		else {
			people.add(person);
			
			personRes.setCode("400");
			personRes.setMessage("Person not added. Name empty ");
			personRes.setPersonResult(people);
			
			return personRes;
			
		}
		
		


	}
	
	public PersonResponse getAllPeople(){
		
		PersonResponse personRes = new PersonResponse();
		
		personRes.setCode("200");			
		personRes.setMessage("People List");			
		personRes.setPersonResult(personDao.selectAllPeople());		
	
		return personRes;
	}
	
	
	public PersonResponse getPersonById(Person person){
		
		
		PersonResponse personRes = new PersonResponse();		
		List<Person> people = new ArrayList<Person>();		
		
		if(validator.idNull(person.getId())) {	
			
			people.add(personDao.selectPersonById(person).orElse(null));
				
			
			
			personRes.setCode("200");
			personRes.setMessage("Id Correct");
			personRes.setPersonResult(people);
				
		}
		
		else {
			personRes.setCode("400");
			personRes.setMessage("Id is empty");
		}
		
		
		
		
		return personRes;		
	}
	
	public PersonResponse deletePerson(Person person) {
		
		
		
		PersonResponse personRes = new PersonResponse();	
		List<Person> people = new ArrayList<Person>();
		
		if(validator.idNull(person.getId())) {		
			
			
			
			
			if(personDao.deletePersonById(person)==1) {
				
				
				people.add(person);				
				personRes.setCode("200");			
				personRes.setMessage("Person deleted");			
				personRes.setPersonResult(people);
			}
			
			
			else {
				
				people.add(person);				
				personRes.setCode("200");			
				personRes.setMessage("Person NOT deleted. Person does not exist");			
				personRes.setPersonResult(people);
				
			}


			
		
		}
		
		else {
			people.add(person);
			
			personRes.setCode("400");
			personRes.setMessage("Id empty");
			personRes.setPersonResult(people);
		}
		
		
		
		
		return personRes;
	}

	public PersonResponse updatePerson(Person newperson) {
		
		PersonResponse personRes = new PersonResponse();	
		List<Person> people = new ArrayList<Person>();
		
		if(validator.idNull(newperson.getId())) {		
			
			if(validator.nameNull(newperson.getName())) {
			
			
			
			if(personDao.updatePersonById(newperson)==1) {
				
				
				people.add(newperson);				
				personRes.setCode("200");			
				personRes.setMessage("Person updated");			
				personRes.setPersonResult(people);
			}
			
			
			else {
				
				people.add(newperson);				
				personRes.setCode("200");			
				personRes.setMessage("Person NOT updated. Person does not exist");			
				personRes.setPersonResult(people);
				
			}
			
		

			
			}
			
			
			
			else {
				people.add(newperson);				
				personRes.setCode("400");
				personRes.setMessage("Name empty");
				personRes.setPersonResult(people);
				
			}
		}
		
		else {
			people.add(newperson);
			
			personRes.setCode("400");
			personRes.setMessage("Id empty");
			personRes.setPersonResult(people);
		}
		
		
		
		return personRes;
		
	}
}
