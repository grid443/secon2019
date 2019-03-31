package org.grid.secon.department.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public List<Department> departmentsByOrg(@RequestParam UUID orgId) {
        return repository.findByOrganizationId(orgId);
    }

    @GetMapping("/all")
    public Iterable<Department> departments() {
        return repository.findAll();
    }
}
