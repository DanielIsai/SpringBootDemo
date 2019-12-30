package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

public interface PersonDao {
	
	void insertPerson(UUID id, Person person);
	
	default void insertPerson(Person person) {
		UUID id = UUID.randomUUID();
		insertPerson(id, person);		
	}
	
	
	List<Person> selectAllPeople();
	
	Optional<Person> selectPersonById(Person personSend);
	
	Optional<Person> selectPersonByName(Person personSend);
	
	boolean deletePersonById(Person person);
	
	boolean updatePersonById(Person person);
	
	
	

}
