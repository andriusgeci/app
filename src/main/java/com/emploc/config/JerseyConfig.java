package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundExceptionMapper;
import com.emploc.validation.ValidationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig() {
        packages("com.emploc.service.rs");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(PersonRestServiceImpl.class);
        register(EntityNotFoundExceptionMapper.class);
        register(BadRequestMapper.class);
        register(ValidationExceptionMapper.class);
    }
}
