package com.poly.Config;

import com.poly.Entity.Authority;
import com.poly.Reponsitory.AuthorityResponsitory;
import com.poly.Service.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component

public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    AuthorityResponsitory authorityResponsitory;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
//        String targetUrl = determineTargetUrl(authentication);
//        if (response.isCommitted()) {
//            System.out.println("Can't redirect");
//            return;
//        }
//        System.out.println(targetUrl);
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }        System.out.println(users);
        Authority authority = authorityResponsitory.findByUserName(users);
        String Roles = authority.getRole().getName();
        if(Roles.equals("admin")){
            redirectStrategy.sendRedirect(request, response, "/admin/managerAccount");
        }else{
            redirectStrategy.sendRedirect(request, response, "/");
        }
    }
    private String determineTargetUrl(Authentication authentication, HttpServletRequest request){
        String url="";
        List<String> roles = SecurityUtils.getAuthorities();
        if(isAdmin(roles)){
            url = "Admin/";

        }else if (isUser(roles)){
            url = "/";
        }
        return url;
    }
    private Boolean isAdmin(List<String> roles){
        if(roles.contains("admin")){
        return true;
        }
        return false;
    }
    private Boolean isUser(List<String> roles){
        if(roles.contains("user")){
            return true;
        }
        return false;
    }
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}

