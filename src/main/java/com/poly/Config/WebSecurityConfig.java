package com.poly.Config;


import com.poly.Entity.Authority;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.AuthorityResponsitory;
import com.poly.Service.CustomOAuth2User;
import com.poly.Service.CustomOAuth2UserService;
import com.poly.Service.UserServiceGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.poly.Service.UserService;
import java.io.IOException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        AuthorityResponsitory authorityResponsitory;
        @Autowired
        UserService userService;
        @Autowired
        AccountReponsitory accountReponsitory;
        @Autowired
        private CustomOAuth2UserService oauthUserService;
        @Autowired
        private UserServiceGoogle userServicegg;

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
                http.csrf().disable().cors().disable(); //

                // phan quyen su dung
                http.authorizeRequests()
                        .antMatchers("/admin/**").hasRole("1")
                        .antMatchers("/addToCart/*", "/cart","/user/**").authenticated()
                        .antMatchers("/api/authorities").hasRole("")
                        .anyRequest().permitAll(); // anonymous

                // dieu khien loi truy cap khong dung vai tro
                  http.exceptionHandling().accessDeniedPage("/login/unauthorized"); // [/error]

                // giao dien dang nhap
                http.formLogin()
                        .loginPage("/auth/signin")
                        .loginProcessingUrl("/auth/signin") // [/login]
                        .successHandler(myAuthenticationSuccessHandler()) // succeshadler la truy cap đúng mk thì sẽ trả về trag url
                        .failureUrl("/login/error") //đăng nhập sai tra ve trang error
                        .usernameParameter("username") // [username]
                        .passwordParameter("password").and().oauth2Login()
                        .loginPage("/auth/signin")
                        .userInfoEndpoint()
                        .userService(oauthUserService)
                        .and()
                        .successHandler(new AuthenticationSuccessHandler() {
                                @Override
                                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                    Authentication authentication) throws IOException, ServletException {
                                        System.out.println("AuthenticationSuccessHandler invoked");
                                        System.out.println("Authentication name: " + authentication.getName());
                                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                                        userServicegg.processOAuthPostLogin(oauthUser.getEmail(),authentication);
                                        String users = oauthUser.getAttribute("email");
                                        Authority authority = authorityResponsitory.findByUserName(users);
                                        String Roles = authority.getRole().getName();
                                        if(Roles.equals("admin")){
                                                response.sendRedirect( "/admin/managerOrder");
                                        }else{
                                                response.sendRedirect( "/");
                                        }
                                }
                        })
                ;
                // [password]
                http.rememberMe().rememberMeParameter("remember").tokenValiditySeconds(86400); // [remember-me]

                // dang xuat
                http.logout()
                        .logoutUrl("/auth/logoff") // [/logout]
                        .logoutSuccessUrl("/auth/signin").and().sessionManagement().maximumSessions(1).expiredUrl("/auth/signin"); // nguoi dung k the login 2 trinh duyet
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
                web
                        .ignoring()
                        .antMatchers("/data.json"); // Đặt đường dẫn tới file JSON cần truy cập ở đây
        }
        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
                return new CustomSuccessHandler();
        }

}