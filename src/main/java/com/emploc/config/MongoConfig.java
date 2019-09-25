package com.emploc.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
}
