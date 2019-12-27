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
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Person;
import com.example.demo.model.PersonResponse;
import com.example.demo.util.Validator;


@Service
public class PersonService {
	

	
	private final PersonDao personDao;
	private final Validator validator;
	private final MessageResponse messageResponse;
	private final PersonResponse personResponse;
	
	private final List<Person> people = new ArrayList<Person>();
	
	@Autowired						
	public PersonService(@Qualifier("fakeDao") PersonDao personDao, Validator validator, MessageResponse messageResponse, PersonResponse personResponse) {
		this.personDao = personDao;
		this.validator = validator;
		this.messageResponse = messageResponse;
		this.personResponse = personResponse;
	}

	
	
	public PersonResponse addPerson(Person person) {
		people.clear();
		people.add(person);
		if(validator.name(person.getName())) {
			personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mSuccess(), people);
			personDao.insertPerson(person);
		}
		else {
			personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mEmptyName(), people);
		}
		return personResponse;
	}
	
	public PersonResponse getAllPeople(){
		personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mGetList(), personDao.selectAllPeople());
		return personResponse;
	}
	
	public PersonResponse getPersonById(Person person){
		people.clear();
		if(validator.id(person.getId())) {	
			if(!personDao.selectPersonById(person).isEmpty()) {
				people.add(personDao.selectPersonById(person).orElse(null));
				personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mGetList(), people);
			}
			else {
				personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mNotExist(), people);				
			}
		}
		else {
			personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mEmptyId(), people);
		}
		return personResponse;		
	}
	
	public PersonResponse deletePerson(Person person) {
		people.clear();
		people.add(person);
		if(validator.id(person.getId())) {		
			if(personDao.deletePersonById(person)) {
				personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mSuccess(), people);
			}
			else {
				personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mNotExist(), people);
			}
		}
		else {
			personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mEmptyId(), people);
		}
		return personResponse;
	}

	public PersonResponse updatePerson(Person newperson) {
		people.clear();
		people.add(newperson);
		if(validator.id(newperson.getId())) {		
			if(validator.name(newperson.getName())) {
				if(personDao.updatePersonById(newperson)) {
					personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mSuccess(), people);
				}
				else {
					personResponse.setPersonResponse(messageResponse.codeSuccess(), messageResponse.mNotExist(), people);
				}
			}
			else {
				personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mEmptyName(), people);
			}
		}
		else {
			personResponse.setPersonResponse(messageResponse.codeError(), messageResponse.mEmptyId(), people);
		}
		return personResponse;
	}
}
