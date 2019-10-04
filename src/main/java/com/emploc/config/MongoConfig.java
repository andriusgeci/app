/*
package com.emploc.config;

import com.emploc.model.Person;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Configuration
@EnableMongoRepositories(basePackages = "com.emploc.repository")
@PropertySource("classpath:mongodb.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    private Environment env;

    @Autowired
    public MongoConfig(Environment env) {
        this.env = env;
    }

    @Override
    public MongoClient mongoClient() {

        return new MongoClient(env.getProperty("mongo.host"), Integer.parseInt(Objects.requireNonNull(env.getProperty("mongo.port"))));
    }

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.database");
    }

    @PostConstruct
    public void createMongoIndexes(){
        MongoClient mongoClient = mongoClient();
        MongoDatabase db = mongoClient.getDatabase(getDatabaseName());
        MongoCollection<Document> personMongoCollection = db.getCollection("persons");
        personMongoCollection.createIndex(Indexes.text("pName"));

       // "{ 'pName' : { '$regex' : '^nameOnE$', '$options' : 'i' "

        for (Document index : personMongoCollection.listIndexes()) {
            System.out.println("TESTTTTTT"+index.toJson());
        }

    }


}
*/
