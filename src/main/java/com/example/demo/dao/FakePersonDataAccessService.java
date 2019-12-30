package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;


@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

	private static List<Person> DB = new ArrayList<>();
	
	@Override
	public void insertPerson(UUID id, Person person) {
		DB.add(new Person(id,person.getName()));
	}

	@Override
	public List<Person> selectAllPeople() {
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(Person personSend) {	
		UUID id = personSend.getId();
		return DB.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}
	
	
	@Override
	public Optional<Person> selectPersonByName(Person personSend) {
		String name = personSend.getName();
		return DB.stream()
				.filter(person -> person.getName().equals(name))
				.findFirst();
	}


	@Override
	public boolean deletePersonById(Person person) {
		
		UUID id = person.getId();
		Optional<Person> personMaybe = selectPersonById(person);
		if(personMaybe.isEmpty()) { return false; }		
		DB.remove(personMaybe.get());
		return true;
	}

	@Override
	public boolean updatePersonById(Person update) {

		return selectPersonById(update)
				.map(person ->{
					int indexOfPersonToUpdate = DB.indexOf(person);
					if(indexOfPersonToUpdate >= 0) {
						DB.set(indexOfPersonToUpdate, new Person(update.getId(),update.getName()));
						return true;						
					}					
					return false;				
				})
				.orElse(false);		
	}


}
