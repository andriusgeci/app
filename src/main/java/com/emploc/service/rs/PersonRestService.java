package com.emploc.service.rs;

import com.emploc.model.CodeMessage;
import com.emploc.model.Person;
import io.swagger.annotations.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.emploc.utils.AppConstants.*;

@Api
@Path("/service/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PersonRestService {

    @GET
    @Path("/person/{pClockCardNo}")
    @ApiOperation(value = "get Person object", tags = {"Person"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MSG, response = Person.class),
            @ApiResponse(code = 400, message = ERR_MSG, response = CodeMessage.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = NOT_FOUND_MSG, response = CodeMessage.class, responseContainer = "List"),
    })
    Response getPerson(
            @ApiParam(value = "Person clock card number", required = true) @NotNull @PathParam("pClockCardNo") String pClockCardNo
    );

    @POST
    @Path("/person")
    @ApiOperation(value = "Create person object", tags = {"Person"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MSG, response = Person.class),
            @ApiResponse(code = 400, message = ERR_MSG, response = CodeMessage.class, responseContainer = "List"),
    })
    Response createPerson(
            @ApiParam(value = "Person object", required = true) @NotNull Person person
    );

    @PUT
    @Path("person/{pClockCardNo}")
    @ApiOperation(value = "update Person object", tags = {"Person"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MSG, response = Person.class),
            @ApiResponse(code = 400, message = ERR_MSG, response = CodeMessage.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = NOT_FOUND_MSG, response = CodeMessage.class, responseContainer = "List")
    })
    Response updatePerson(
            @ApiParam(value = "Person clock card number", required = true) @NotNull @PathParam("pClockCardNo") String pClockCardNo,
            @ApiParam(value = "Person object", required = true) @NotNull Person person);
}
