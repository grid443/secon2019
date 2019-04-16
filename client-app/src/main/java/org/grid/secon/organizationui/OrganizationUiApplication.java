package org.grid.secon.organizationui;

import org.grid.secon.organizationui.config.mvc.MvcConfig;
import org.grid.secon.organizationui.config.security.SecurityConfig;
import org.grid.secon.organizationui.config.security.WebClientConfig;
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
