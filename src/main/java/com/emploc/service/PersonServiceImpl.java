package com.emploc.service;

import com.emploc.model.ListableResponse;
import com.emploc.model.Person;
import com.emploc.model.PersonFilter;
import com.emploc.repository.PersonRepository;
import com.emploc.utils.RsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    private MongoTemplate mongoTemplate;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Validator validator, MongoTemplate mongoTemplate) {

        this.personRepository = personRepository;
        this.validator = validator;
        this.mongoTemplate = mongoTemplate;
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
    public ListableResponse<Person> findPerson(PersonFilter personFilter) {
        final ListableResponse<Person> result = new ListableResponse<>();
        final List<Person> personList = mongoTemplate
                .find(Query.query(Criteria.where(personFilter.getKey())
                        .regex(personFilter.getValue(), "i")), Person.class);
        if (personList.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, personFilter.getValue()));
        }
        result.setItems(personList);
        return result;
    }
}
