package com.sap.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sap.domain.Course;
import com.sap.service.CourseService;

@Controller 
public class CourseController {
    @Autowired
    private CourseService courseService;

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
 
    @RequestMapping("/")    
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView("index");   
        Course course = courseService.selectCourseById(1);
//        Map<String, Course> courseMap = new HashMap<String, Course>();
//        for(int i = 0; i < courseList.size(); i++) {
//        	courseMap.put(new Integer(i).toString(), courseList.get(i));
//        }
        mav.addObject("course", course);   
        return mav;    
    }    
    
    @RequestMapping("/course")    
    public String getCourse(Model model){
        log.info("Hello Course");
        ModelAndView mav = new ModelAndView("course");   
        List<Course> courseList = courseService.selectCourseByChain(1);
        model.addAttribute("courseList", courseList);
        return "course";
    }
}
