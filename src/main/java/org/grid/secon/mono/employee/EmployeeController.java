package org.grid.secon.mono.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public String departmentsByOrg(Model model, @RequestParam UUID orgId) {
        model.addAttribute("employees", repository.findByDepartmentId(orgId));
        return "department/list";
    }
}
