package org.grid.secon.clientapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .mvcMatchers("/organization/**").hasAuthority("SCOPE_organization")
                .mvcMatchers("/department/**").hasAuthority("SCOPE_department")
                .anyRequest().authenticated()
                .and()
            .oauth2Login()
                .userInfoEndpoint().oidcUserService(oidcUserService())
                .and()
                .loginPage("/oauth2/authorization/client-app")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("http://localhost:8700/uaa/logout.do?client_id=client-app&redirect=http://localhost:8701")
                .and()
            .oauth2Client();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new ScopedOidcUserService();
    }
}
