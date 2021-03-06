package org.grid.secon.mono.department;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends CrudRepository<Department, UUID> {
    List<Department> findByOrganizationId(UUID organizationId);
}
