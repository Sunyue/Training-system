package com.sap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sap.dao.CourseDao;
import com.sap.domain.Course;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired 
    private CourseDao courseDao;  
 
    public Course selectCourseById(Integer courseId) {  
        return courseDao.selectCourseById(courseId);
    }
	public List<Course> selectCourseByChain(Integer chainId) {
		// TODO Auto-generated method stub
		return courseDao.selectCourseByChain(chainId);
	}
}
