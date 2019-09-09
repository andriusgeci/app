package com.emploc.repository;

import com.emploc.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    @Query("{'pName':?0}")
    List<Person> findPersonByName(String pName);
}
