/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.configuration;

import edu.mum.authentication.UserAccountTemplate;
import edu.mum.service.IUserCrudRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author 985939
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserAccountTemplate.class)
public class WebMvcSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsService detailsService;
    
    @Autowired
    public void authenticator(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean(name = "bcyptEncryption")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                    .antMatchers("/user/").access("hasRole('ROLE_USER')")
                    .anyRequest().permitAll()
                    .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("uName")
                    .passwordParameter("pWord")
                    .and()
                    .logout().logoutSuccessUrl("/login?logout")
                    .and()
                    .exceptionHandling().accessDeniedPage("/403")
                    .and()
                    .csrf();                    
    }
}
