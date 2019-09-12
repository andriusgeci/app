package com.emploc.validation;

import com.emploc.model.CodeMessage;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(final EntityNotFoundException exception) {
        final List<CodeMessage> response = new ArrayList<>();
        response.add(new CodeMessage().code(Response.Status.NOT_FOUND.getStatusCode()).message(exception.getMessage()));
        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }
}
