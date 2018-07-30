package com.sap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(HttpSession session, Model model){
        return "logon";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model){
        return "logon";
    }


}
