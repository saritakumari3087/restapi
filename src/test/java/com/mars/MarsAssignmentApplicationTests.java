package com.mars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.hamcrest.core.StringContains;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.mars.controller.PersonController;
import com.mars.exception.PersonNotFoundException;
import com.mars.model.Person;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MarsAssignmentApplicationTests {

	@Autowired
	private PersonController personController;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		assertThat(personController).isNotNull();
	}


	@Test
	public void addPersonTest() throws JSONException {
		Person person = new Person();
		person.setId(1l);
		person.setFirstName("sarita");
		person.setLastName("kumari");
		ResponseEntity<Person> actual =restTemplate.postForEntity("http://localhost:" + port + "/api/person",person, Person.class);
		
		Assertions.assertEquals(200,actual.getStatusCodeValue());
		Assertions.assertEquals(Person.class,actual.getBody().getClass());
				
	
	}
	@Test
	public void listPersonTest() throws JSONException {
		
		Assertions.assertEquals(200,restTemplate.getForEntity("http://localhost:" + port + "/api/person",Object.class).getStatusCodeValue());
		
				
	
	}
	@Test
	public void editPersonTest() {
		Assertions.assertEquals(404, restTemplate.getForEntity("http://localhost:" + port + "/person/56",Person.class).getStatusCodeValue());
	
	}
	
	@Test
	public void deletePersonTest() {
		Assertions.assertEquals(404, restTemplate.getForEntity("http://localhost:" + port + "/person/56",Person.class).getStatusCodeValue());
		String result =restTemplate.getForEntity("http://localhost:" + port + "/person/56",String.class).getBody();
		Assertions.assertNotEquals("{\"timestamp\":\"2020-12-21T11:39:23.945+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/person/56\"}", StringContains.containsString(result));
		 
		
	}
	
	
}
