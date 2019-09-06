package com.emploc.service;

import com.emploc.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person getPersonById(int personId);

    Person savePerson(Person person);

    List<Person> listPersonByName(String name);
}
