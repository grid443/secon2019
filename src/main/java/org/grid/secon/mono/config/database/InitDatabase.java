package org.grid.secon.mono.config.database;

import org.grid.secon.mono.config.security.userdetails.User;
import org.grid.secon.mono.config.security.userdetails.UserRepository;
import org.grid.secon.mono.organization.Organization;
import org.grid.secon.mono.organization.OrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitDatabase {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, OrganizationRepository organizationRepository) {
        return (args) -> {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            User accountant = new User();
            accountant.setLogin("auditor");
            accountant.setPassword(encoder.encode("auditor"));
            accountant.setRole("AUDITOR");
            userRepository.save(accountant);

            User admin = new User();
            admin.setLogin("accountant");
            admin.setPassword(encoder.encode("accountant"));
            admin.setRole("ACCOUNTANT");
            userRepository.save(admin);

            Organization org = new Organization();
            org.setName("horns and hoofs");
            organizationRepository.save(org);
        };
    }
}
