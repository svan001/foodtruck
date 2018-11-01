package com.svan001.foodtruck.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/static/img").permitAll()
                .and().authorizeRequests().antMatchers("/public/**").permitAll()
                // TODO to test access denied
                .and().authorizeRequests().antMatchers("/alwaysdenied").denyAll()
                // TODO Needed for H2 console => DEV TO REMOVE
                .and().authorizeRequests().antMatchers("/console/**").permitAll()
                .and().authorizeRequests().antMatchers("/test").fullyAuthenticated()
                .and().authorizeRequests().antMatchers("/**").permitAll()
                // basics
                .and().formLogin()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/access_denied.html")
        ;

        // TODO Needed for H2 console => DEV TO REMOVE
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        // Override encryptedPassword encoding that will delegate to other encoder based on encryptedPassword prefix
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //TODO a suppr just for dev purpose
        PasswordEncoder devNoEncryptpPassordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(devNoEncryptpPassordEncoder);

        return delegatingPasswordEncoder;
    }
}
