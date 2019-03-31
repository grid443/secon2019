package org.grid.secon.organizationui.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final WebClient webClient;

    public DepartmentController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/list")
    public ModelAndView departmentsByOrg(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient, @RequestParam UUID orgId) {
        String uri = "http://localhost:8703/department/list?orgId=" + orgId.toString();
        List<Map<String, String>> departments = loadDepartments(authorizedClient, uri);
        return new ModelAndView("department/list", Collections.singletonMap("departments", departments));
    }

    @GetMapping("/all")
    public ModelAndView departments(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient) {
        String uri = "http://localhost:8703/department/all";
        List<Map<String, String>> departments = loadDepartments(authorizedClient, uri);
        return new ModelAndView("department/list", Collections.singletonMap("departments", departments));
    }

    private List<Map<String, String>> loadDepartments(OAuth2AuthorizedClient authorizedClient, String uri) {
        return webClient
                .get()
                .uri(uri)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, String>>>() {
                })
                .block();
    }
}
