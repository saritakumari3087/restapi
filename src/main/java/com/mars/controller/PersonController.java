package com.mars.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.exception.PersonNotFoundException;
import com.mars.model.Person;
import com.mars.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/person")
	public Person addPerson(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@GetMapping("/person")
	public ResponseEntity<?> listPerson() {
		return (ResponseEntity<?>) personRepository.findAll();
	}

	@PutMapping("/person/{id}")
	public Person editPerson(@PathVariable(value = "id") Long id, @RequestBody Person personDetails) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));

		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());

		Person updatedPerson = personRepository.save(person);
		return updatedPerson;
	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));

		personRepository.delete(person);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/personcount")
	public Long countPerson() {
		return personRepository.count();
	}

}
