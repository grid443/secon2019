package org.grid.secon.mono.config.database;

import org.grid.secon.mono.config.security.userdetails.User;
import org.grid.secon.mono.config.security.userdetails.UserRepository;
import org.grid.secon.mono.organization.Organization;
import org.grid.secon.mono.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitDatabase {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, OrganizationRepository organizationRepository) {
        return (args) -> {
            User auditor = new User();
            auditor.setLogin("auditor");
            auditor.setPassword(passwordEncoder.encode("auditor"));
            auditor.setRole("ROLE_AUDITOR");
            userRepository.save(auditor);

            User accountant = new User();
            accountant.setLogin("accountant");
            accountant.setPassword(passwordEncoder.encode("accountant"));
            accountant.setRole("ROLE_ACCOUNTANT");
            userRepository.save(accountant);

            Organization org = new Organization();
            org.setType("llc.");
            org.setName("horns and hoofs");
            organizationRepository.save(org);
        };
    }
}
