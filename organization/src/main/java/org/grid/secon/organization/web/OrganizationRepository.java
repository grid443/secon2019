package org.grid.secon.organization.web;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
}
