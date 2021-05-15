package com.example.tp_jpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.w3c.dom.ls.LSOutput;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder=passwordEncoder();
        System.out.println("*********************");
        System.out.println(passwordEncoder.encode("12345"));
        System.out.println("*********************");

        auth.inMemoryAuthentication().withUser("ahouzi123").password(passwordEncoder.encode("12345")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("12345")).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("12345")).roles("ADMIN","USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        //http.httpBasic(); formulaire d'authentification basic http
        http.authorizeRequests().antMatchers("/save*/*","/delete*/*","/form**/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
