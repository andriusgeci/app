package com.emploc.service;

import com.emploc.model.ListableResponse;
import com.emploc.model.Person;
import com.emploc.model.PersonFilter;

public interface PersonService {

    Person getPersonById(String pClockCardNo);

    Person savePerson(Person person);

    Person saveOld(Person person);

    Person deletePersonById(String pClockCardNo);

    ListableResponse<Person> findPerson(PersonFilter personFilter);
}
