package org.grid.secon.clientapp;

import org.grid.secon.clientapp.config.mvc.MvcConfig;
import org.grid.secon.clientapp.config.security.SecurityConfig;
import org.grid.secon.clientapp.config.security.WebClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({MvcConfig.class, SecurityConfig.class, WebClientConfig.class})
@SpringBootApplication
public class OrganizationUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationUiApplication.class, args);
    }
}
