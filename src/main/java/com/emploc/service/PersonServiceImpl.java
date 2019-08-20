package com.emploc.service;

import com.emploc.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


@Service
public class PersonServiceImpl implements PersonService {


    static private Map<Integer, Person> personDB = new
            ConcurrentHashMap<Integer, Person>();
    static private AtomicInteger idCounter = new AtomicInteger();

    MongoClient mongoClient = MongoClients.create();
    MongoDatabase database = mongoClient.getDatabase("mongoDB");

    MongoCollection<Document> collection = database.getCollection("persons");



    @Override
    public Person getPersonById(int pesonId) {
     createPerson();
//        Person person = personDB.get(pesonId);
        Document query = new Document("pName", "Andrius");
        List results = new ArrayList<>();
        System.out.println("QUERY");
        collection.find(query).into(results);
        System.out.println("Andrius"+results);




        return (Person) results.get(0);
    }

    public void createPerson() {


        /*MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDatabase("test");
        boolean auth = database.authenticate("username", "pwd".toCharArray());*/
        Person p = new Person();
        p.setPId(idCounter.incrementAndGet());
        p.setPName("Andrius");
        p.setPSurename("surename");
        p.setPSeatNo(2);
        p.setPDepartment("Department");
        p.setPFloor("third floor");
        p.setPLiveSupportNo("live 1");
        System.out.println(p);
        Document person = new Document().append("Andrius", p);
        System.out.println("INSERTING");
        collection.insertOne(person);
        /*personDB.put(p.getPId(), p);*/
    }
}
