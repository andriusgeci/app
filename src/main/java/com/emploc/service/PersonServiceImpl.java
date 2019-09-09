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
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getPersonById(String clockCardNo) {
        try {
            Optional<Person> opt = personRepository.findById(clockCardNo);
            if (opt.isEmpty()) {
                throw new EntityNotFoundException("Entity with requested id: " + clockCardNo + " not found");
            }
            return opt.get();
        } finally {
            System.out.println("zzz");
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
