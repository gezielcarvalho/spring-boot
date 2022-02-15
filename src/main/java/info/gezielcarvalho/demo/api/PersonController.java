package info.gezielcarvalho.demo.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.gezielcarvalho.demo.model.Person;
import info.gezielcarvalho.demo.service.PersonService;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
	
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping
	public void addPerson(@RequestBody @NotNull @Valid Person person) {
		personService.addPerson(person);
	}
	
	@GetMapping
	public List<Person> getAllPeople(){
		return personService.getAllPeople();
	}
	
	@GetMapping(path = "/{id}")
	public Person getPersonById(@PathVariable("id") UUID id) {
		return personService.getPersonById(id)
				.orElse(null);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deletePersonById(@PathVariable("id") UUID id) {
		personService.deletePersonById(id);
	}
	
	@PutMapping(path = "/{id}")
	public void updatePerson(@PathVariable("id") UUID id, @NotNull @RequestBody @Valid Person person) {
		personService.updatePersonById(id, person);
	}	
}
