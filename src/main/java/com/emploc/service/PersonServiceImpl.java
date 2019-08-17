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
        createPerson();
        Person person = personDB.get(pesonId);
        return person;
    }


    public void createPerson() {

        Person p = new Person();
        p.setPId(idCounter.incrementAndGet());
        p.setPName("Andrius");
        p.setPSurename("surename");
        p.setPSeatNo(2);
        p.setPDepartment("Department");
        p.setPFloor("third floor");
        p.setPLiveSupportNo("live 1");
        personDB.put(p.getPId(), p);
    }
}
