package com.sap.service;
 
import org.junit.Test;  
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.baseTest.SpringTestCase;
import com.sap.domain.Course;
 
public class CourseServiceTest extends SpringTestCase {
 
    @Autowired 
    private CourseService courseService; 
 
    @Test 
    public void selectUserByIdTest(){  
        Course course = courseService.selectCourseById(1);  
        System.out.println(course.getCourseName() + ":" + course.getDescription());
    }  
}
