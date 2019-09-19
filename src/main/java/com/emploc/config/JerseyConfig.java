package com.emploc.config;

import com.emploc.service.rs.PersonRestServiceImpl;
import com.emploc.validation.BadRequestMapper;
import com.emploc.validation.EntityNotFoundMapper;
import com.emploc.validation.ValidationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    /*@Value("${server.contextPath}")*/
    private String serverContextPath="/";

   /* @PostConstruct
    private void configure() {
        this.configureSwagger();
    }*/

    public JerseyConfig() {
        register(PersonRestServiceImpl.class);
        register(EntityNotFoundMapper.class);
        register(BadRequestMapper.class);
        register(ValidationExceptionMapper.class);
    }


    /*OpenAPI oas = new OpenAPI();

    Info info = new Info()
            .title("Swagger Sample App bootstrap code")
            .description("This is a sample server Petstore server.  You can find out more about Swagger " +
                    "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
                    "you can use the api key `special-key` to test the authorization filters.")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact()
                    .email("apiteam@swagger.io"))
            .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    oas
*/



}
