package com.svan001.foodtruck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests().antMatchers("/**")
                .fullyAuthenticated()
                // TODO Needed for H2 console => DEV TO REMOVE
                .and().authorizeRequests().antMatchers("/console/**")
                .permitAll();

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
