package com.sap.dao;
 
import java.util.List;

import com.sap.domain.Course;
import org.springframework.stereotype.Component;

@Component
public interface CourseDao {
 
    /**
     * @param courseId
     * @return Course
     */
    public Course selectCourseById(Integer courseId);  
    
    public List<Course> selectCourseByChain(Integer chainId);
 
}
