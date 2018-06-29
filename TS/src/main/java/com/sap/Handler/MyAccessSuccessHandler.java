package com.sap.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(MyAccessSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = "";
        for( GrantedAuthority s: authentication.getAuthorities()){
            role = s.getAuthority();
            if(role.length()>0){
                break;
            }
        }

        if (authentication != null) {
            logger.info("User '" + authentication.getName()  +"' with role '" + role
                    + "' attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        if(role.equals("ROLE_S")){
            response.sendRedirect(request.getContextPath() + "/student/");
        }else if (role.equals("ROLE_A")){
            response.sendRedirect(request.getContextPath() + "/admin/");
        }else {
            response.sendRedirect(request.getContextPath() + "/403");
        }
    }
}
