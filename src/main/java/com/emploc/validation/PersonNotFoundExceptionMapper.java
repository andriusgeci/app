package com.emploc.validation;

import com.emploc.model.CodeMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;
import java.util.List;

public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

    @Override
    public Response toResponse(final PersonNotFoundException exception) {
        final List<CodeMessage> response = new ArrayList<>();
        exception.getViolations().forEach(violation -> response.add(
                new CodeMessage().code(Response.Status.NOT_FOUND.getStatusCode()).message(violation)));
        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }
}
