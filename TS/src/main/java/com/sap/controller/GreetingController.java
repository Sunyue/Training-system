package com.sap.controller;


import com.sap.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Course greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.info("Hello World");
        Course c = new Course();
        c.setCourseId(1);
        c.setCourseName("Test");
        c.setDescription("Description");

        return c;
    }

    @RequestMapping("/getSession")
    public String login(HttpSession session, @RequestParam("username") String username){

        if (session.getAttribute(username) == null){
            return "empty";
        }else {
            return "Already logged in";
        }
    }
}
