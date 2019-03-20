package org.grid.secon.mono.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findByDepartmentId(UUID departmentId);
}
