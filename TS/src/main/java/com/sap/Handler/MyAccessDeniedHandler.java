package com.sap.Handler;

import com.sap.Constant.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for( GrantedAuthority s: auth.getAuthorities()){
            role = s.getAuthority();
            if(role.length()>0){
                break;
            }
        }

        if (auth != null) {
            logger.info("User '" + auth.getName()  +"' with role '" + role
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }

        if(role.equals("ROLE_"+Consts.studentRole)){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Consts.studentHomePage);
        }else if (role.equals("ROLE_"+Consts.adminRole)){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Consts.adminHomePage);
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Consts.pageNotFound);
        }
    }
}