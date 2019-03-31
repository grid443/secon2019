package org.grid.secon.organization.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationRepository repository;

    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Iterable<Organization> all() {
        return repository.findAll();
    }

    @GetMapping("/read")
    public String read() {
        return "OK";
    }

    @GetMapping("/edit")
    public String edit() {
        return "OK";
    }
}
