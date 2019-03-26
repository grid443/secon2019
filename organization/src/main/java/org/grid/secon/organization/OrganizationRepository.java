package org.grid.secon.organization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
}
