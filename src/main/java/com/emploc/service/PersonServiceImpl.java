package com.emploc.service;

import com.emploc.model.Person;
import com.emploc.model.PersonFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private Validator validator;

    @Autowired
    public PersonServiceImpl(Validator validator) {

        this.validator = validator;
    }

    @Override
    public Person getPersonById(String pClockCardNo) {
        /*Optional<Person> opt = personRepository.findById(pClockCardNo);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, pClockCardNo));
        }*/
        return null;
    }

    @Override
    public Person savePerson(Person person) {
        //personRepository.save(person);
        return person;
    }

    @Override
    public Person saveOld(Person person) {
       /* final Optional<Person> saved = personRepository.findById(person.getPClockCardNo());
        RsCheck.validate(person, validator, ".");
        if (saved.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, person.getPClockCardNo()));
        }
        personRepository.save(person);*/
        return person;

    }

    @Override
    public Person deletePerson(String pClockCardNo) {
        /*final Optional<Person> delete = personRepository.findById(pClockCardNo);
        if (delete.isEmpty()) {
            throw new EntityNotFoundException(String.format("%s : %s", NOT_FOUND_MSG, pClockCardNo));
        }
        personRepository.deleteById(pClockCardNo);*/
        return null;
    }

    @Override
    public List<Person> findPerson(PersonFilter personFilter) throws NoSuchFieldException, IllegalAccessException {
        /*System.out.println("Hello");
        Person matcherObject = new Person();
        Query q = new Query();
        q.addCriteria(Criteria.where(personFilter.getKey()).regex(personFilter.getValue(),"$caseSensitive"));*/
      /* String name = matcherObject.getClass().getDeclaredField(personFilter.getKey()).set;
        matcherObject.setpName("nameOne");*/
        //System.out.println("TESTING"+person.getClass().getFields().length+"===="+person.getClass().getField("pName"));
        //matcherObject.setpName("nameone");
        // ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher(person.toString(),exact().ignoreCase());
        //ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withMatcher(person.toString(),exact().ignoreCase());
        // Example<Person> example = Example.of(person, matcher);
       /* Pattern p = Pattern.compile("Mon.*DB", CASE_INSENSITIVE);
        db.Employee.find({EmployeeName:{$regex: "Gu",$options:'i'}}).forEach(printjson)*/
        //  matcherObject.getClass().getDeclaredField(personFilter.getKey());
        /*ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(personFilter.getKEY(),exact());
        Example<Person> example = Example.of(matcherObject, matcher);
        System.out.println("test"+matcherObject.toString());*/
        return null;
    }
/*
    final ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher("roles", match -> match.transform(source -> ((BasicDBList) source).iterator().next()).caseSensitive());
    users = userRepository.findAll(Example.of(criteria, matcher), pageRequest);*/

}
