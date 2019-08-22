package com.emploc.service;

import com.emploc.model.Person;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Service
public class PersonServiceImpl implements PersonService {

    MongoClient mongoClient = MongoClients.create();

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoDatabase mongoDatabase = mongoClient.getDatabase("mongoDB").withCodecRegistry(pojoCodecRegistry);
    MongoCollection<Person> collection = mongoDatabase.getCollection("persons", Person.class);

    @Override
    public Person getPersonById(int personId) {

        Document query = new Document("pId", personId);
        List<Person> results = new ArrayList<>();
        System.out.println("WORKING");
        collection.find(query).into(results);
        System.out.println("Andrius" + results.get(0));
        //Person person = results.get();
        return results.get(0);
    }

    @Override
    public Person savePerson(Person person) {
        System.out.println("INSERTING PERSON" + person);
        collection.insertOne(person);
        return person;
    }
}
