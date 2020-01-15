package com.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsService memberFinderService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .mvcMatchers("/", "/v1/members/**")
                    .permitAll()

                .mvcMatchers("/v1/admin")
                    .hasRole("ADMIN")

                .anyRequest()
                    .authenticated();

        http.formLogin()
                .usernameParameter("memberEmail")
                .passwordParameter("memberPassword")
                .permitAll();

        http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberFinderService).passwordEncoder(passwordEncoder());
    }
}
