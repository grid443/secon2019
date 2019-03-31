package org.grid.secon.organization.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final OAuth2ResourceServerProperties resourceServerProperties;

    @Autowired
    public SecurityConfig(OAuth2ResourceServerProperties resourceServerProperties) {
        this.resourceServerProperties = resourceServerProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .mvcMatchers("/organization/read").hasAuthority("SCOPE_organization.read")
                .mvcMatchers("/organization/edit").hasAuthority("SCOPE_organization.edit")
                .mvcMatchers("/organization/**").hasAuthority("SCOPE_organization")
                .anyRequest().authenticated()
                .and()
            .oauth2ResourceServer()
                .jwt()
                    .jwkSetUri(this.resourceServerProperties.getJwt().getJwkSetUri());
    }
}
