package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;


@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

	@Override
	public void insertPerson(UUID id, Person person) {		
	}
	
	@Override
	public List<Person> selectAllPeople() {
		
		return List.of(new Person(UUID.randomUUID(), "FROM THIS DB"));
	}

	@Override
	public int deletePersonById(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePersonById(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Person> selectPersonById(Person personSend) {
		// TODO Auto-generated method stub
		return null;
	}

}