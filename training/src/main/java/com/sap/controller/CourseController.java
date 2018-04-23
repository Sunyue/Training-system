package com.sap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sap.domain.Course;
import com.sap.service.CourseService;

@Controller 
public class CourseController {
    @Resource 
    private CourseService courseService;  
 
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
    public ModelAndView getCourse(){      
        ModelAndView mav = new ModelAndView("course");   
        List<Course> courseList = courseService.selectCourseByChain(1);
//        Map<String, Course> courseMap = new HashMap<String, Course>();
//        for(int i = 0; i < courseList.size(); i++) {
//        	courseMap.put(new Integer(i).toString(), courseList.get(i));
//        }
        mav.addObject("courseList", courseList);   
        return mav;    
    }    
}
