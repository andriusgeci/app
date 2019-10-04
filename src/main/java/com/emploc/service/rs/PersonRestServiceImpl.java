package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.model.PersonFilter;
import com.emploc.service.PersonService;
import com.emploc.utils.RsCheck;
import com.emploc.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static com.emploc.utils.AppConstants.*;

@Slf4j
@Service
public class PersonRestServiceImpl implements PersonRestService {

    private final PersonService personService;
    private Validator validator;

    @Autowired
    public PersonRestServiceImpl(PersonService personService, Validator validator) {

        this.personService = personService;
        this.validator = validator;
    }

    @Override
    public Response getPerson(@NotNull String pClockCardNo) {
        final StopWatch timer = StopWatch.createStarted();
        log.info("start: getPerson  clockCardNo = {}", pClockCardNo);
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(pClockCardNo), WRONG_ID_LABEL);
            final Person person = personService.getPersonById(pClockCardNo);
            return Response.ok(person).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in getPerson: {}", e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(LOG_CURLY_BRACES, TIME_ELAPSED_MS, String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response createPerson(@NotNull final Person person) {
        final StopWatch timer = StopWatch.createStarted();
        log.info("start: createPerson {}", String.valueOf(person));
        try {
            RsCheck.validate(person, validator, ".");
            return Response.ok(personService.savePerson(person)).build();
        } catch (final ValidationException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in createPerson: {}", e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(LOG_CURLY_BRACES, TIME_ELAPSED_MS, String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response updatePerson(@NotNull String pClockCardNo, @NotNull Person person) {
        final StopWatch timer = StopWatch.createStarted();
        person.setPClockCardNo(pClockCardNo);
        log.info("start: updatePerson {} with clockCardId {} ", person, pClockCardNo);
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(pClockCardNo), WRONG_ID_LABEL);
            RsCheck.validate(person, validator, ".");
            return Response.ok(personService.saveOld(person)).build();
        } catch (final ValidationException | BadRequestException e) {
            throw e;
        } catch (final EntityNotFoundException noIdException) {
            if (StringUtils.isBlank(noIdException.getMessage())) {
                throw new EntityNotFoundException(NOT_FOUND_MSG + noIdException.getCause());
            }
            throw noIdException;
        } catch (final Exception e) {
            log.warn("error occurred in updatePerson: {}", e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(LOG_CURLY_BRACES, TIME_ELAPSED_MS, String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response deletePerson(@NotNull String pClockCardNo) {
        final StopWatch timer = StopWatch.createStarted();
        log.info("start: deletePerson with clockCardId {} ", pClockCardNo);
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(pClockCardNo), WRONG_ID_LABEL);
            return Response.ok(personService.deletePerson(pClockCardNo)).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in deletePerson: {}", e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(LOG_CURLY_BRACES, TIME_ELAPSED_MS, String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response listPersonsByName(PersonFilter person) {
        final StopWatch timer = StopWatch.createStarted();
        log.info("start: listPersons with {} ", person);
        try {
            /*RsCheck.badRequest(StringUtils.isNoneBlank(key), WRONG_ID_LABEL);
            RsCheck.badRequest(StringUtils.isNoneBlank(pair), WRONG_ID_LABEL);*/
            return Response.ok(personService.findPerson(person)).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("error occurred in listPersonsByName: {}", e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info(LOG_CURLY_BRACES, TIME_ELAPSED_MS, String.valueOf(timer.getTime()));
        }
    }
}
