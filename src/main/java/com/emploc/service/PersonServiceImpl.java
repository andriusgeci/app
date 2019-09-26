package com.emploc.service;

import com.emploc.config.MongoConfig;
import com.emploc.model.Person;
import com.emploc.repository.PersonRepository;
import com.emploc.utils.RsCheck;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.emploc.utils.AppConstants.NOT_FOUND_MSG;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private Validator validator;
    MorphiaQuery morphia;
    MongoDatabase datastore;

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
    public List<Person> getPersonByName(String pName) {
        Person person = new Person();
        QUser   user = new QUser("user");
        MorphiaQuery<Person> query = new MorphiaQuery<Person>(morphia, datastore, user);
        List<Person> list = query
                .where(user.firstName.eq("Bob"))
                .fetch();
       // Predicate predicate = Person.getPName().eq(pName);
        /*List<Person> persons = queryFactory.selectFrom(person)
                .where(
                        person.firstName.eq("John"),
                        person.lastName.eq("Doe"))
                .fetch();*/
        //MongoCollection<Person> collection = database.getCollection("restaurants");
       // collection.createIndex(Indexes.text("pName"));

        /* = personRepository.findPersonByName(pName);
        if (p.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, pName));
        }*/
        return null;
    }


}
