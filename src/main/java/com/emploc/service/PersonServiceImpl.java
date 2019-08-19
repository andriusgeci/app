package com.emploc.service;

import com.emploc.model.Person;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Service;

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

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);

    MongoDatabase database = mongoClient.getDatabase("testdb");

    MongoCollection<Person> collection = database.getCollection("person",Person.class);

    public void create(){

        Person ada = new Person(1,2,"test","test","test","test","test");

        collection.insertOne(ada);

    }

    @Override
    public Person getPersonById(int pesonId) {
//        createPerson();
//        Person person = personDB.get(pesonId);

        create();
        collection.find().forEach(printBlock);
        return person;
    }

    Block<Person> printBlock = new Block<Person>() {
        @Override
        public void apply(final Person person) {
            System.out.println(person);
        }
    };
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
        personDB.put(p.getPId(), p);
    }
}
