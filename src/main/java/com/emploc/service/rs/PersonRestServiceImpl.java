package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.model.person.Personroot;
import com.emploc.service.PersonService;
import com.emploc.utils.RsCheck;
import com.emploc.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static com.emploc.utils.AppConstants.TIME_ELAPSED_MS;
import static com.emploc.utils.AppConstants.WRONG_ID_LABEL;

@Slf4j
@Service
public class PersonRestServiceImpl implements PersonRestService {

    private final PersonService personService;

    @Autowired
    public PersonRestServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Response getPerson(@NotNull String pClockCardNo) {
        log.info("start: getPerson  clockCardNo = {}", pClockCardNo);
        final StopWatch timer = StopWatch.createStarted();
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(pClockCardNo), WRONG_ID_LABEL);
            final Person person = personService.getPersonById(pClockCardNo);
            return Response.ok(person).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in getPerson: " + e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(TIME_ELAPSED_MS + " {}", String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response createPerson(final Person<Personroot> person) {
        final StopWatch timer = StopWatch.createStarted();
        log.info("start: createPerson {}", String.valueOf(person));
        try {
            return Response.ok(personService.savePerson(person)).build();
        } catch (final ValidationException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in createPerson: " + e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(TIME_ELAPSED_MS + " {}", String.valueOf(timer.getTime()));
        }
    }
}
