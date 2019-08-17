package com.emploc.service;

import com.emploc.model.Person;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonServiceImpl implements PersonService {


    static private Map<Integer, Person> personDB = new
            ConcurrentHashMap<Integer, Person>();
    static private AtomicInteger idCounter = new AtomicInteger();



    @Override
    public Person getPersonById(int pesonId) {
        Person p = new Person();
        p.setId(idCounter.incrementAndGet());
        p.setName("Andrius");
        personDB.put(p.getId(), p);
        Person person = personDB.get(pesonId);
        return person;
    }
}
