package com.emploc.service.rs;

import com.emploc.model.Person;
import com.emploc.service.PersonService;
import com.emploc.utils.RsCheck;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;

import static com.emploc.utils.AppConstants.WRONG_ID_LABEL;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

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
            /* RsCheck.badRequest(StringUtils.isNoneBlank("personId"),WRONG_ID_LABEL);*/
            return Response.ok(person).build();
        } catch (final ValidationException | BadRequestException e) {
            throw e;

        } catch (final Exception e) {
            throw new BadRequestException(e);
        } finally {
            System.out.println(timer.getTime());
        }
    }


    /*@Override
    public Response bhoGet(final String xAegProfileId, final String xAttTransactionId,
                           @NotNull final String bhoId) {
        logInfo(LOGGER, new MetaBuilder().setReason("Start: getBHO").setKeyAndValue(X_AEG_PROFILE_ID, xAegProfileId)
                .setKeyAndValue(BHO_ID_MSG, bhoId));
        final StopWatch timer = StopWatch.createStarted();
        try {
            RsCheck.badRequest(StringUtils.isNoneBlank(bhoId), WRONG_ID_LABEL);
            final BroadBandHomeEntity result = bhoService.getEntityById(bhoId);
            return Response.ok(result).build();
        } catch (final ValidationException | DocumentDoesNotExistException | BadRequestException e) {
            throw e;
        } catch (final Exception e) {
            logWarn(LOGGER, new MetaBuilder().fromException(e, getClass().getName()).setStackTrace(e));
            throw new BadRequestException(e);
        } finally {
            logInfo(LOGGER, new MetaBuilder().setReason("Finish: getBHO").setKeyAndValue(ELAPSE_TIME_MS,
                    String.valueOf(timer.getTime())));
        }
    }*/
}
