package com.emploc.validation;

import com.emploc.model.CodeMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(final ValidationException exception) {

        final List<CodeMessage> response = new ArrayList<>();
        exception.getViolations().forEach(violation -> response.add(
                new CodeMessage().code(Response.Status.BAD_REQUEST.getStatusCode()).message(violation)));
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
