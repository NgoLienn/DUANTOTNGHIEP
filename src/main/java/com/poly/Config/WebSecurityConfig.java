package com.poly.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.poly.Service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserService userService;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // CDRP, CORS
                http.csrf().disable().cors().disable();

                // phan quyen su dung
                http.authorizeRequests()
                                .antMatchers("/addToCart/*").authenticated()
                                .antMatchers("/admin/**").hasAnyRole("ADMIN", "DIRE").antMatchers("/api/authorities")
                                .hasRole("DIRE")
                                // .antMatchers("/home/users").hasAnyRole("ADMIN","USER")
                                .anyRequest().permitAll();// anonymous

                // dieu khien loi truy cap khong dung vai tro
                http.exceptionHandling().accessDeniedPage("/login/unauthoried");// [/error]
                // â
                // giao dien dang nhap
                http.formLogin().loginPage("/auth/signin")
                                .loginProcessingUrl("/auth/signin")// [/login]
                                .defaultSuccessUrl("/", false).failureUrl("/login/error")
                                .usernameParameter("username") // [username]
                                .passwordParameter("password");// [password]
                http.rememberMe().rememberMeParameter("remember").tokenValiditySeconds(86400); // [remember-me]

                // dang xuat
                http.logout().logoutUrl("/auth/logoff")// [/logout]
                                .logoutSuccessUrl("/auth/signin");// chuyen trang

        }

}
