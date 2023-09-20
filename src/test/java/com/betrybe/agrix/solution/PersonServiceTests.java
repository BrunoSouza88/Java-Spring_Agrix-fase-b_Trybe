package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * javadoc.
 */
@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTests {

  @Autowired
  PersonService personService;

  @MockBean
  private PersonRepository personRepository;

  @Test
  @DisplayName("Test Person Creation")
  public void testPersonCreation() {
    Person person = new Person();
    person.setUsername("User");
    person.setPassword("User4321");
    person.setId(54321L);

    Person personToReturn = new Person();
    personToReturn.setId(54321L);
    personToReturn.setUsername(person.getUsername());
    personToReturn.setPassword(person.getPassword());

    Mockito.when(personRepository.save(any(Person.class)))
        .thenReturn(personToReturn);

    Person savedPerson = personService.create(person);

    Mockito.verify(personRepository).save(any(Person.class));

    Assertions.assertEquals(person.getUsername(), savedPerson.getUsername());
    Assertions.assertEquals(person.getPassword(), savedPerson.getPassword());
    Assertions.assertEquals(person.getId(), savedPerson.getId());
  }

  @Test
  @DisplayName("Test Person Not Found")
  public void testPersonNotFound() {
    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("654L"));

    Mockito.verify(personRepository).findByUsername(ArgumentMatchers.eq("654L"));
  }

  @Test
  @DisplayName("Test Person by Id")
  public void testPersonId() {
    Person person = new Person();
    person.setId(4321L);

    Mockito.when(personRepository.findById(ArgumentMatchers.eq(4321L)))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(4321L);

    Mockito.verify(personRepository).findById(ArgumentMatchers.eq(4321L));

    Assertions.assertEquals(returnedPerson.getId(), person.getId());

  }



}
