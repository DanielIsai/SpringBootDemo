package com.example.demo.api;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Person;
import com.example.demo.model.PersonResponse;
import com.example.demo.service.PersonService;

@Controller
public class WebController {
	
	private final PersonService personService;
	private PersonResponse personResponse;
		
	public WebController(PersonService personService, PersonResponse personResponse) {
		this.personService = personService;
		this.personResponse = personResponse;
	}

	@GetMapping("/Create")
	public String create() {
		return "AddPerson";
	}
	
	@PostMapping("/detailsCreate")
	public String detailsCreate(	@RequestParam("cname") String cname, ModelMap modelMap) {
		Person newperson = new Person(null,cname);
		personService.addPerson(newperson);
		personResponse = personService.getPersonByName(newperson);
		
		modelMap.put("page", "Added");
		return "Successful";
	}
	
	@GetMapping("/ObtainAll")
	public String ObtainAll(ModelMap modelMap) {
		
		List<Person> list = personService.getAllPeople().getPersonResult();
		String chain = "";
		
		for(int i = 0; i< list.size(); i++) {		
		 chain += "[  Id: " + list.get(i).getId().toString() + " , Name:  " + list.get(i).getName().toString() + "  ]" ;
		}
		modelMap.put("list", chain);
		
		return "ViewAll";
	}
	
	@GetMapping("/ObtainById")
	public String ObtainById() {
		return "ObtainById";
	}
	
	@PostMapping("/detailsObtainById")
	public String detailsObtainById(@RequestParam("cid") UUID cid, ModelMap modelMap) {
		Person newperson = new Person(cid,"");		
		personResponse = personService.getPersonById(newperson);
		String cname = personResponse.getPersonResult().get(0).getName();
		modelMap.put("cid", cid);
		modelMap.put("cname", cname);
		return "ViewCostumers";
	}
	
	
	@GetMapping("/DeleteById")
	public String DeleteById() {
		return "DeleteById";
	}
	
	@PostMapping("/detailsDeleteById")
	public String detailsDededById(@RequestParam("cid") UUID cid, ModelMap modelMap) {
		Person newperson = new Person(cid,"");		
		personResponse = personService.deletePerson(newperson);
		modelMap.put("page", "Deleted");
		return "Successful";
	}
	
	
	@GetMapping("/UpdateById")
	public String UpdateById() {
		return "UpdatedById";
	}
	
	@PostMapping("/detailsUpdateById")
	public String detailsUpdateById(@RequestParam("cid") UUID cid, @RequestParam("cname") String cname, ModelMap modelMap) {
		Person newperson = new Person(cid,cname);		
		personResponse = personService.updatePerson(newperson);
		modelMap.put("page", "Updated");		
		return "Successful";
		
		
		
	}
	
	
	
	
	
	

}
