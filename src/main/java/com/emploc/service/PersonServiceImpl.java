package com.emploc.service;

import com.emploc.validation.PersonNotFoundException;
import com.emploc.model.Person;
import com.emploc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getPersonById(int personId) throws Exception {

        /*try {
            Optional<Person> opt = personRepository.findById(personId);
            if (opt.isEmpty()) {

                throw new Exception();//throw document not found
            }
            final Person person = opt.get();*/
            return personRepository.findById(personId).get();
                   /* .orElseThrow(() -> new PersonNotFoundException("Person with id" + personId + "Not Found"));*/
      /*  } finally {
            System.out.println("test");
        }*/


    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> listPersonByName(String name) {
        personRepository.findPersonByName(name);
        return null;
    }

}
