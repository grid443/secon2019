package org.grid.secon.clientapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
    private WebClient webClient;

    @Autowired
    public OrganizationController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/list")
    public ModelAndView list(@RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient authorizedClient) {
        List<Map<String, String>> organizations = loadOrganizations(authorizedClient);
        return new ModelAndView("organization/list", Collections.singletonMap("orgs", organizations));
    }

    @GetMapping("/read")
    public ModelAndView read(
            @RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient uiClient,
            @RegisteredOAuth2AuthorizedClient("organization-read") OAuth2AuthorizedClient readClient
    ) {
        Map<String, Object> model = new HashMap<>();
        model.put("orgs", loadOrganizations(uiClient));
        model.put("read", "OK".equals(accessOrganization(readClient, "read")));
        return new ModelAndView("organization/list", model);
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RegisteredOAuth2AuthorizedClient("organization-ui") OAuth2AuthorizedClient uiClient,
            @RegisteredOAuth2AuthorizedClient("organization-edit") OAuth2AuthorizedClient editClient
    ) {
        Map<String, Object> model = new HashMap<>();
        model.put("orgs", loadOrganizations(uiClient));
        model.put("edit", "OK".equals(accessOrganization(editClient, "edit")));
        return new ModelAndView("organization/list", model);
    }

    private List<Map<String, String>> loadOrganizations(OAuth2AuthorizedClient authorizedClient) {
        return webClient
                .get()
                .uri("http://localhost:8702/organization/list")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, String>>>() {
                })
                .block();
    }

    private String accessOrganization(OAuth2AuthorizedClient authorizedClient, String operation) {
        return webClient
                .get()
                .uri("http://localhost:8702/organization/" + operation)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
