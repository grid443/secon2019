package org.grid.secon.clientapp.web;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final WebClient webClient;

    @Autowired
    public DepartmentController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/list")
    public ModelAndView departmentsByOrg(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient, @RequestParam UUID orgId) {
        String uri = "http://localhost:8703/department/list?orgId=" + orgId.toString();
        List<Map<String, String>> departments = callService(
                authorizedClient,
                uri,
                new ParameterizedTypeReference<List<Map<String, String>>>() {
                }
        );
        return new ModelAndView("department/list", Collections.singletonMap("departments", departments));
    }

    @GetMapping("/all")
    public ModelAndView departments(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient) {
        String uri = "http://localhost:8703/department/all";
        List<Map<String, String>> departments = callService(
                authorizedClient,
                uri,
                new ParameterizedTypeReference<List<Map<String, String>>>() {
                }
        );
        return new ModelAndView("department/list", Collections.singletonMap("departments", departments));
    }

    @GetMapping("/validate")
    public ModelAndView validate(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient, @RequestParam UUID departmentId) {
        String uri = "http://localhost:8703/department/validate?departmentId=" + departmentId.toString();
        Map<String, String> department = callService(
                authorizedClient,
                uri,
                new ParameterizedTypeReference<Map<String, String>>() {
                }
        );
        return new ModelAndView("department/validated", department);
    }

    private <T> T callService(OAuth2AuthorizedClient authorizedClient, String uri, ParameterizedTypeReference<T> returnType) {
        return webClient
                .get()
                .uri(uri)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(returnType)
                .block();
    }
}
