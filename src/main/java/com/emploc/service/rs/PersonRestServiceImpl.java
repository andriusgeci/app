package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.service.PersonService;
import com.emploc.utils.RsCheck;
import com.emploc.validation.EntityNotFoundException;
import com.emploc.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static com.emploc.utils.AppConstants.WRONG_ID_LABEL;

@Service
@Slf4j
public class PersonRestServiceImpl implements PersonRestService {

    private final PersonService personService;

    @Autowired
    public PersonRestServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Response getPerson(@NotNull final String clockCardNo) {
        log.info("start: getPerson  clockCardNo = {}", clockCardNo);
        final StopWatch timer = StopWatch.createStarted();
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(clockCardNo), WRONG_ID_LABEL);
            final Person person = personService.getPersonById(clockCardNo);
            return Response.ok(person).build();
        } catch (final ValidationException | EntityNotFoundException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            log.warn("andrius" + e.getMessage());
            throw new BadRequestException(e);
        } finally {
            log.info("time elapsed ms {}", String.valueOf(timer.getTime()));
        }
    }

    @Override
    public Response createPerson(@NotNull final Person person) {
        try {
            // RsCheck.badRequest(person.getPayload() != null, "payload may not be null");
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
