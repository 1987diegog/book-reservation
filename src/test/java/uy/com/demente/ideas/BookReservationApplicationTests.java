package uy.com.demente.ideas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uy.com.demente.ideas.business.services.PersonService;
import uy.com.demente.ideas.view.resources.dto.PersonDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookReservationApplicationTests {

	@Autowired
	public PersonService personService;

	@Test
	void createPersonTest() {

		PersonDTO newPerson = new PersonDTO();
		
		newPerson.setName("Diego");
		newPerson.setLastName("González");
		newPerson.setEmail("1987diegog@gmail.com");
		newPerson.setStreetAddress("Rambla m58s17");
		newPerson.setAge(32);

		PersonDTO result = personService.create(newPerson);

		assertTrue(result.getName().equals("Diego"));
	}

}
