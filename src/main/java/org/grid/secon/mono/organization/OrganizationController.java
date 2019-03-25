package org.grid.secon.mono.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationRepository repository;

    @Autowired
    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("orgs", repository.findAll());
        return "organization/list";
    }

    @GetMapping("/read")
    public String read(Model model) {
        model.addAttribute("orgs", repository.findAll());
        model.addAttribute("read", true);
        return "organization/list";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("orgs", repository.findAll());
        model.addAttribute("edit", true);
        return "organization/list";
    }
}
