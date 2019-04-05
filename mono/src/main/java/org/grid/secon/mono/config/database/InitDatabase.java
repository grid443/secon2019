package org.grid.secon.mono.config.database;

import org.grid.secon.mono.config.security.userdetails.User;
import org.grid.secon.mono.config.security.userdetails.UserAuthority;
import org.grid.secon.mono.config.security.userdetails.UserRepository;
import org.grid.secon.mono.department.Department;
import org.grid.secon.mono.department.DepartmentRepository;
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
    public CommandLineRunner loadData(UserRepository userRepository, OrganizationRepository organizationRepository, DepartmentRepository departmentRepository) {
        return (args) -> {
            User auditor = new User();
            auditor.setLogin("auditor");
            auditor.setPassword(passwordEncoder.encode("password"));
            auditor.setRole("AUDITOR");
            auditor.addAuthority(new UserAuthority("READ_ORGANIZATION"));
            userRepository.save(auditor);

            User orgAuditor = new User();
            orgAuditor.setLogin("org_auditor");
            orgAuditor.setPassword(passwordEncoder.encode("password"));
            orgAuditor.setRole("ORG_AUDITOR");
            orgAuditor.addAuthority(new UserAuthority("READ_ORGANIZATION"));
            userRepository.save(orgAuditor);

            User accountant = new User();
            accountant.setLogin("accountant");
            accountant.setPassword(passwordEncoder.encode("password"));
            accountant.setRole("ACCOUNTANT");
            accountant.addAuthority(new UserAuthority("READ_ORGANIZATION"));
            accountant.addAuthority(new UserAuthority("EDIT_ORGANIZATION"));
            userRepository.save(accountant);

            Organization org = new Organization();
            org.setType("llc.");
            org.setName("horns and hoofs");
            organizationRepository.save(org);

            Department department = new Department();
            department.setName("no");
            department.setLocation("nowhere");
            department.setOrganizationId(org.getId());
            departmentRepository.save(department);
        };
    }
}
