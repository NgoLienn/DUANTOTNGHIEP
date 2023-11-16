package com.poly.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
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
                                .antMatchers("/addToCart/*","/cart","/user/order", "/user/orderitem").authenticated()
                                // .antMatchers("/admin/**").hasRole("admin")
                                .antMatchers("/api/authorities").hasRole("")
                                .anyRequest().permitAll(); // anonymous

                // dieu khien loi truy cap khong dung vai tro
                http.exceptionHandling().accessDeniedPage("/login/unauthorized"); // [/error]

                // giao dien dang nhap
                http.formLogin()
                                .loginPage("/auth/signin")
                                .loginProcessingUrl("/auth/signin") // [/login]
                                .successHandler((req, res, auth) -> {
                                        for (GrantedAuthority authority : auth.getAuthorities()) {
                                                if ("admin".equals(authority.getAuthority())) {
                                                        res.sendRedirect("/admin");
                                                }
                                        }
                                        res.sendRedirect("/");
                                })
                                .failureUrl("/login/error")
                                .usernameParameter("username") // [username]
                                .passwordParameter("password"); // [password]

                http.rememberMe().rememberMeParameter("remember").tokenValiditySeconds(86400); // [remember-me]

                // dang xuat
                http.logout()
                                .logoutUrl("/auth/logoff") // [/logout]
                                .logoutSuccessUrl("/auth/signin"); // chuyen trang
        }

}
