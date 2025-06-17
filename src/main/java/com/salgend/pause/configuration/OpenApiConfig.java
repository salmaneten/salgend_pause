package com.salgend.pause.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Salgend Pause Restaurant API",
        version = "1.0.0",
        description = "Restaurant Management System API",
        contact = @Contact(
            name = "Salgend Team",
            email = "zerradisalmane2@gmail.com"
        )
    )
)
public class OpenApiConfig {
    
}
