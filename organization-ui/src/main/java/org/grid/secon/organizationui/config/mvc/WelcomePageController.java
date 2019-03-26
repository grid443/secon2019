package org.grid.secon.organizationui.config.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomePageController {
    @RequestMapping("/")
    public String redirectPage() {
        return "redirect:/organization/list";
    }
}
