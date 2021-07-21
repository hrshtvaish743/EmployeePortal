package com.harshit.employeeportal.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
    }
}
