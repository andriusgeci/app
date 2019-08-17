package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

public class PersonRestServiceImpl implements PersonRestService {

    private final PersonService personService;

    @Autowired
    public PersonRestServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Response getPerson(@NotNull int personId) {
        final Person person = personService.getPersonById(personId);
        return Response.ok(person).build();
    }
}
