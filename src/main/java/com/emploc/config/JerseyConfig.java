package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundExceptionMapper;
import com.emploc.validation.ValidationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    private static final Logger log = Logger.getLogger(JerseyConfig.class.getName());

    public JerseyConfig() {
        register(PersonRestServiceImpl.class);
        register(EntityNotFoundExceptionMapper.class);
        register(BadRequestMapper.class);
        register(ValidationExceptionMapper.class);
    }
}
