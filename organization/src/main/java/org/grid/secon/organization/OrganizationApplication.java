package org.grid.secon.organization;

import org.grid.secon.organization.config.database.InitDatabase;
import org.grid.secon.organization.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({InitDatabase.class, SecurityConfig.class})
@SpringBootApplication
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }

}
