package com.emploc.service;

import com.emploc.model.Person;
import com.emploc.repository.PersonRepository;
import com.emploc.validation.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getPersonById(int personId) {


        //return personRepository.findById(personId).isPresent() ?  personRepository.findById(personId).get() : null;
        try {
            Optional<Person> opt = personRepository.findById(personId);
            if (opt.isEmpty()) {
                throw new EntityNotFoundException("Entity with requested id: " + personId + " not found");
            }
            return opt.get();
        } finally {
            System.out.println("finally");
        }
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
