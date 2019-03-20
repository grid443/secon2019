package org.grid.secon.mono;

import org.grid.secon.mono.config.database.InitDatabase;
import org.grid.secon.mono.config.mvc.MvcConfig;
import org.grid.secon.mono.config.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({MvcConfig.class, WebSecurityConfig.class, InitDatabase.class})
@SpringBootApplication
public class MonoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonoApplication.class, args);
    }
}
