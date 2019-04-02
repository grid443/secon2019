package org.grid.secon.department.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController("/department/validate")
public class DepartmentValidationController {
    private final DepartmentRepository repository;
    private final WebClient webClient;

    @Autowired
    public DepartmentValidationController(DepartmentRepository repository, WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    @GetMapping
    public Map<String, String> validate(
            @RegisteredOAuth2AuthorizedClient("department-validate") OAuth2AuthorizedClient validationClient,
            @RequestParam UUID departmentId
    ) {
        Optional<Department> department = repository.findById(departmentId);
        if (!department.isPresent()) {
            return Collections.emptyMap();
        }
        Map<String, String> departmentData = departmentData(department.get());
        departmentData.put("valid", callValidationService(validationClient, departmentId));
        return departmentData;
    }

    private Map<String, String> departmentData(Department department) {
        Map<String, String> departmentData = new HashMap<>();
        departmentData.put("id", department.getId().toString());
        departmentData.put("name", department.getName());
        departmentData.put("location", department.getLocation());
        return departmentData;
    }

    private String callValidationService(OAuth2AuthorizedClient validationClient, UUID departmentId) {
        String result = webClient
                .get()
                .uri("http://localhost:8704/validate?id=" + departmentId)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(validationClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "OK".equals(result) ? "true" : "false";
    }
}