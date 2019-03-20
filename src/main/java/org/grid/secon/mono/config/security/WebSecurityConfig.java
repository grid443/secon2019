package org.grid.secon.mono.config.security;

import org.grid.secon.mono.config.security.userdetails.DatabaseUserDetailsService;
import org.grid.secon.mono.config.security.userdetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/organisation/**").access("hasAnyAuthority('ACCOUNTANT', 'AUDITOR')")
                .antMatchers("/department/**").access("hasAnyAuthority('ACCOUNTANT', 'AUDITOR')")
                .antMatchers("/employee/**").access("hasAnyAuthority('ACCOUNTANT')")
                .antMatchers("/login", "/login?error").anonymous()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService(userRepository);
    }
}
