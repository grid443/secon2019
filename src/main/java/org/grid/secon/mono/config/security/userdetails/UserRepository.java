package org.grid.secon.mono.config.security.userdetails;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByLogin(String login);
}
