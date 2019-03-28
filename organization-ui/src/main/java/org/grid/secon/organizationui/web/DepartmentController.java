package org.grid.secon.organizationui.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/department")
public class DepartmentController {
//    private final DepartmentRepository repository;

//    public DepartmentController(DepartmentRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping("/list")
    public ModelAndView departmentsByOrg(@RequestParam UUID orgId) {
        Map<String, Object> model = new HashMap<>();
//        model.put("departments", repository.findByOrganizationId(orgId));
        return new ModelAndView("department/list", model);
    }
}
