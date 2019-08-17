package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(PersonRestServiceImpl.class);
    }
}
