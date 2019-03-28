package org.grid.secon.organization.config.database;

import org.grid.secon.organization.Organization;
import org.grid.secon.organization.OrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class InitDatabase {
    @Bean
    public CommandLineRunner loadData(OrganizationRepository organizationRepository) {
        return (args) -> {
            Organization org = new Organization();
            org.setId(UUID.fromString("ffedd1eb-4de5-4f45-bb32-c3424ca6b543"));
            org.setType("llc.");
            org.setName("horns and hoofs");
            organizationRepository.save(org);
        };
    }
}
