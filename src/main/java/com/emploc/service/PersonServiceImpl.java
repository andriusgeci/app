package com.emploc.service;

import com.emploc.model.Person;
import com.emploc.repository.PersonRepository;
import com.emploc.utils.RsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

import static com.emploc.utils.AppConstants.NOT_FOUND_MSG;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private Validator validator;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Validator validator) {

        this.personRepository = personRepository;
        this.validator = validator;
    }

    @Override
    public Person getPersonById(String pClockCardNo) {
        Optional<Person> opt = personRepository.findById(pClockCardNo);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_MSG + pClockCardNo);
        }
        return opt.get();
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Person saveOld(Person person) {
        final Optional<Person> saved = personRepository.findById(person.getPClockCardNo());
        RsCheck.validate(person, validator, ".");
        if (saved.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_MSG + person.getPClockCardNo());
        }
        personRepository.save(person);
        return person;

    }

    @Override
    public Person deletePerson(String pClockCardNo) {
        final Optional<Person> delete = personRepository.findById(pClockCardNo);
        if (delete.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_MSG + pClockCardNo);
        }
        personRepository.deleteById(pClockCardNo);
        return delete.get();
    }

    @Override
    public List<Person> listPersonByName(String name) {
        personRepository.findPersonByName(name);
        return null;
    }
}
