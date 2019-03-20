package org.grid.secon.mono.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrganizationController {
    private final OrganizationRepository repository;

    @Autowired
    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String redirectPage() {
        return "redirect:/organisations";
    }

    @GetMapping("/organisations")
    public String all(Model model) {
        model.addAttribute("orgs", repository.findAll());
        return "organisation/list";
    }
}
