package org.grid.secon.department.config.database;

import org.grid.secon.department.Department;
import org.grid.secon.department.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class InitDatabase {
    @Bean
    public CommandLineRunner loadData(DepartmentRepository departmentRepository) {
        return (args) -> {
            Department department = new Department();
            department.setName("no");
            department.setLocation("nowhere");
            department.setOrganizationId(UUID.fromString("ffedd1eb-4de5-4f45-bb32-c3424ca6b543"));
            departmentRepository.save(department);
        };
    }
}
