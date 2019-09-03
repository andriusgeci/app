package com.emploc.validation;

import com.emploc.model.CodeMessage;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class BadRequestMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(final BadRequestException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(Collections.singletonList(
                new CodeMessage().code(Response.Status.BAD_REQUEST.getStatusCode()).message(exception.getMessage())
        )).build();
    }
}
