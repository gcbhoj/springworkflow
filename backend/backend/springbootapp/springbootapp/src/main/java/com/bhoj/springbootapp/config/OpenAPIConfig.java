package com.bhoj.springbootapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Bhoj GC",
                        email = "gcbhoj@gmail.com"
                ),
                description = "Open API documentation for {{APP NAME}}",
                title = "Open API specification - {{APP NAME}}",
                version = "1.0",
                license = @License(
                        name = "Licence Name",
                        url = "https://some-url.com"
                )
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:5000/api/v1"
                ),
                @Server(
                        description = "Production Environment",
                        url = "https://github.com"
                )
        }
//        security = {
//                @SecurityRequirement(name = "bearerAuth")
//        }
)
//@SecurityScheme(
//        name = "bearerAuth",
//        description = "JWT authentication",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class OpenAPIConfig {
}
