package org.grid.secon.department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface DepartmentRepository extends CrudRepository<Department, UUID> {
    List<Department> findByOrganizationId(UUID organizationId);
}
