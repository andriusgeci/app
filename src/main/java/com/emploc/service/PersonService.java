package com.emploc.service;

import com.emploc.model.Person;

public interface PersonService {

 Person getPersonById(int personId);
 Person savePerson(Person person);

}
