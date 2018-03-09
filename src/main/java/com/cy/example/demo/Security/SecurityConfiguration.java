package com.cy.example.demo.Security;

import com.cy.example.demo.Model.AppUserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.hibernate.criterion.Restrictions.and;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    //    HttpSecurity: tells us which routes people are allowed to acesses includes methods to restict or alllow access
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()
                .antMatchers("/","/h2-console/**","/images/**","/register","/mainpage", "/top", "/assets/**","/css/**","/testapi","searchtopic").permitAll()
                .antMatchers( "/entertainment", "/business", "/sports").access("hasAuthority('USER') or  hasAuthority('ADMIN')")
                .antMatchers("/listfounditemadm", "/listlostitemadm", "/processupdstatus/**","addusertoreport","savdusertoreport").access("hasAuthority('ADMIN')")

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{

        PasswordEncoder pE = passwordEncoder();
        auth.inMemoryAuthentication().withUser("username").password(pE.encode("password")).authorities("USER")
                .and().withUser("admin").password(pE.encode("password")).authorities("ADMIN");
      // auth.inMemoryAuthentication()
      //          .withUser("admin").password("password").authorities("ADMIN")
      //          .and().withUser("user").password("password").authorities("USER");

//        Database Authentication must come after in memory authentication
        auth
                .userDetailsService(userDetailsServiceBean());

    }




}
