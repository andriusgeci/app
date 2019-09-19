package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundMapper;
import com.emploc.validation.ValidationExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    @Value("${server.contextPath}")
    private String serverContextPath;

    @PostConstruct
    private void configure() {
        this.configureSwagger();
    }

    public JerseyConfig() {
        register(PersonRestServiceImpl.class);
        register(EntityNotFoundMapper.class);
        register(BadRequestMapper.class);
        register(ValidationExceptionMapper.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setConfigId("springboot-jersey-swagger");
        swaggerConfig.setTitle("swaggerServiceTitle");
        swaggerConfig.setVersion("apiVersion");
        swaggerConfig.setContact("swaggerApiContact");
        swaggerConfig.setSchemes(new String[]{"http", "https"});
        swaggerConfig.setBasePath(this.serverContextPath);
        swaggerConfig.setResourcePackage(JerseyConfig.class.getPackage().getName());
        swaggerConfig.setPrettyPrint(true);
        swaggerConfig.setScan(true);
        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);
    }
}
