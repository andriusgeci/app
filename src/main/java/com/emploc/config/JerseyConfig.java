package com.emploc.config;

import com.emploc.service.rs.PersonRestService;
import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundMapper;
import com.emploc.validation.ValidationExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {


    /*public JerseyConfig(@Context ServletConfig servletConfig) {
        super();
        OpenAPI openAPI = new OpenAPI();
        Info info = new Info()
                .title("test")
                .description("test")
                .termsOfService("http://swagger.io.terms")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.00html"));
        openAPI.info(info);
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration()
                .openAPI(openAPI)
                .prettyPrint(true)
                .resourcePackages(Stream.of("com.emploc.service.rs").collect(Collectors.toSet()));

        try {
            new JaxrsOpenApiContextBuilder()
                    .servletConfig(servletConfig)
                    .application(this)
                    .openApiConfiguration(swaggerConfiguration)
                    .buildContext(true);
        } catch (OpenApiConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }*/




    private void init() {

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8086");
        beanConfig.setBasePath("/swagger-demo/api");
        beanConfig.setResourcePackage(PersonRestService.class.getPackage().getName());
        beanConfig.setTitle("RESTEasy, Swagger and Swagger UI Example");
        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan(true);
    }

    public JerseyConfig() {
        init();
        register(PersonRestServiceImpl.class);
        register(EntityNotFoundMapper.class);
        register(BadRequestMapper.class);
        register(ValidationExceptionMapper.class);
    }
}
