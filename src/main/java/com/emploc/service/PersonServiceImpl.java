package com.emploc.service;

import com.emploc.model.Person;
import com.emploc.model.PersonFilter;
import com.emploc.repository.PersonRepository;
import com.emploc.utils.RsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.emploc.utils.AppConstants.NOT_FOUND_MSG;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

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
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, pClockCardNo));
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
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, person.getPClockCardNo()));
        }
        personRepository.save(person);
        return person;

    }

    @Override
    public Person deletePerson(String pClockCardNo) {
        final Optional<Person> delete = personRepository.findById(pClockCardNo);
        if (delete.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, pClockCardNo));
        }
        personRepository.deleteById(pClockCardNo);
        return delete.get();
    }

    @Override
    public List<Person> findPerson(Person person) throws NoSuchFieldException {

        Person matcherObject = new Person();
        //System.out.println("TESTING"+person.getClass().getFields().length+"===="+person.getClass().getField("pName"));
        //matcherObject.setpName("nameone");
       // ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher(person.toString(),exact().ignoreCase());
        //ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withMatcher(person.toString(),exact().ignoreCase());
       // Example<Person> example = Example.of(person, matcher);
        Pattern p = Pattern.compile("Mon.*DB", CASE_INSENSITIVE);
        return personRepository.findAll(Example.of(person));
    }
/*
    final ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher("roles", match -> match.transform(source -> ((BasicDBList) source).iterator().next()).caseSensitive());
    users = userRepository.findAll(Example.of(criteria, matcher), pageRequest);*/

}
