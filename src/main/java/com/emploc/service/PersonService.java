package com.emploc.service;

import com.emploc.model.Person;

import java.util.List;

public interface PersonService {

    Person getPersonById(String clockCardNo);

    Person savePerson(Person person);

    List<Person> listPersonByName(String name);
}
