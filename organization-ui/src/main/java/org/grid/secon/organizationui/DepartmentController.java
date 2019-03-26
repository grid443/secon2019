package org.grid.secon.organizationui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public String departmentsByOrg(Model model, @RequestParam UUID orgId) {
        model.addAttribute("departments", repository.findByOrganizationId(orgId));
        return "department/list";
    }
}
