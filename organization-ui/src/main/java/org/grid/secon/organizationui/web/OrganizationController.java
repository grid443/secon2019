package org.grid.secon.organizationui.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
//    private final WebClient webClient;

//    private OAuth2RestTemplate restTemplate;

//    @Autowired
//    public OrganizationController(OrganizationRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping("/list")
    public ModelAndView list() {
        Map<String, Object> model = new HashMap<>();
//        model.put("orgs", repository.findAll());
        return new ModelAndView("organization/list", model);
    }

    @GetMapping("/read")
    public ModelAndView read() {
        Map<String, Object> model = new HashMap<>();
//        model.put("orgs", repository.findAll());
        model.put("read", true);
        return new ModelAndView("organization/list", model);
    }

    @GetMapping("/edit")
    public ModelAndView edit() {
        Map<String, Object> model = new HashMap<>();
//        model.put("orgs", repository.findAll());
        model.put("edit", true);
        return new ModelAndView("organization/list", model);
    }
}
