package com.lifepill.customerservice.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

    @Value("${server.port:8070}")
    private String serverPort;

    @Bean
    public OpenAPI defineOpenApi() {
        Server localServer = new Server()
                .url("http://localhost:" + serverPort)
                .description("Local Development Server");

        Server gatewayServer = new Server()
                .url("http://localhost:9191")
                .description("API Gateway Server");

        Contact contact = new Contact()
                .name("LifePill Team")
                .email("support@lifepill.com")
                .url("https://lifepill.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info information = new Info()
                .title("LifePill Patient Customer Service API")
                .version("1.0.0")
                .description("Customer-side operations API for LifePill Platform. " +
                        "Handles customer registration, prescriptions, payments, and prescription orders.")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(information)
                .servers(List.of(localServer, gatewayServer));
    }
}
