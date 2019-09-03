package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(PersonRestServiceImpl.class);
    }
}
