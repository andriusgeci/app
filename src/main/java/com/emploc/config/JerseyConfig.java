package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundMapper;
import com.emploc.validation.ValidationExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("1");
        config.setTitle("Person Locatio ");
        config.setVersion("1");
        config.setContact("andriusgeci@gmail.com");
        config.setSchemes(new String[]{"HTTP,HTTPS"});
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.emploc.service.rs");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

    private void registerEndpoints() {
        this.register(PersonRestServiceImpl.class);
        this.register(EntityNotFoundMapper.class);
        this.register(BadRequestMapper.class);
        this.register(ValidationExceptionMapper.class);
        this.register(WadlResource.class);
    }
}
