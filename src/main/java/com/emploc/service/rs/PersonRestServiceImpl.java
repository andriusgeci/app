package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.service.PersonService;
import com.emploc.validation.EntityNotFoundException;
import com.emploc.validation.ValidationException;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

@Service
public class PersonRestServiceImpl implements PersonRestService {

    private final PersonService personService;

    @Autowired
    public PersonRestServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Response getPerson(@NotNull int personId) {

        final StopWatch timer = StopWatch.createStarted();
        try {
            final Person person = personService.getPersonById(personId);
            return Response.ok(person).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public Response createPerson(@NotNull final Person person) {
        try {
            // RsCheck.badRequest(bco.getPayload() != null, "payload may not be null");
            System.out.println("PersonRestServiceIMPL" + person);
            return Response.ok(personService.savePerson(person)).build();
        } catch (final ValidationException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            // logWarn(LOGGER, new MetaBuilder().fromException(e, getClass().getName()).setStackTrace(e));
            throw new BadRequestException(e);
        } finally {
            // logInfo(LOGGER, new MetaBuilder().setReason("Finish: createBCO").setKeyAndValue(ELAPSE_TIME_MS,
            //String.valueOf(timer.getTime())));
        }
    }
}
