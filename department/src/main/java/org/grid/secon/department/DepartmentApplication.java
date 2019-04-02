package org.grid.secon.department;

import org.grid.secon.department.config.database.InitDatabase;
import org.grid.secon.department.config.security.SecurityConfig;
import org.grid.secon.department.config.security.WebClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({InitDatabase.class, SecurityConfig.class, WebClientConfig.class})
@SpringBootApplication
public class DepartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentApplication.class, args);
    }

}
