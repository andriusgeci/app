package com.emploc.validation;

import com.emploc.model.CodeMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {


    @Override
    public Response toResponse(final EntityNotFoundException exception) {
        System.out.println("PersonNotFoundExceptionMapper");
        final List<CodeMessage> response = new ArrayList<>();
        //return Response.status(Response.Status.NOT_FOUND).entity("ERROR" + e.getMessage()).build();
        response.add(new CodeMessage().code(Response.Status.NOT_FOUND.getStatusCode()).message(exception.getMessage()));
        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }
}
