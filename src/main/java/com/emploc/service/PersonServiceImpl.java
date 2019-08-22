package com.emploc.service;

import com.emploc.model.Person;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Service
public class PersonServiceImpl implements PersonService {


    static private Map<Integer, Person> personDB = new
            ConcurrentHashMap<Integer, Person>();
    static private AtomicInteger idCounter = new AtomicInteger();


    MongoClient mongoClient = MongoClients.create();

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoDatabase mongoDatabase = mongoClient.getDatabase("mongoDB").withCodecRegistry(pojoCodecRegistry);
    MongoCollection<Person> collection = mongoDatabase.getCollection("persons", Person.class);


   /* MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .build();
    MongoClient mongoClient = (MongoClient) MongoClients.create(settings);*/

    @Override
    public Person getPersonById(int pesonId) {
        //createPerson();
//        Person person = personDB.get(pesonId);
        //Document query = new Document("pName", "Andrius");
        List results = new ArrayList<>();
        System.out.println("WORKING");
        //collection.find(query).into(results);
        //System.out.println("Andrius" + results);


        return (Person) results.get(0);
    }

    @Override
    public Person createPerson(Person person) {
        System.out.println("INSERTING PERSON"+ person);
        collection.insertOne(person);
        return person;
    }

    /*public Person createPerson() {


     *//*MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDatabase("test");
        boolean auth = database.authenticate("username", "pwd".toCharArray());*//*

        Person p = new Person();
        p.setPId(idCounter.incrementAndGet());
        p.setPName("Andrius");
        p.setPSurename("surename");
        p.setPSeatNo(2);
        p.setPDepartment("Department");
        p.setPFloor("third floor");
        p.setPLiveSupportNo("live 1");
        System.out.println(p);
        *//* Document person = new Document().append("Andrius1", p);*//*
        System.out.println("INSERTING");
        collection.insertOne(p);
        *//*personDB.put(p.getPId(), p);*//*
    }*/
}
