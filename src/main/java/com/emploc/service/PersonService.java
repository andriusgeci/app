package com.emploc.service;

import com.emploc.model.Person;

import java.util.List;

public interface PersonService {

    Person getPersonById(String pClockCardNo);

    Person savePerson(Person person);

    Person saveOld(Person person);

    List<Person> listPersonByName(String name);
}
