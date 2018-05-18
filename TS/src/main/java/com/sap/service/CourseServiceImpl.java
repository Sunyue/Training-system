package com.sap.service;

import java.util.List;

import com.sap.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sap.dao.CourseDao;
import com.sap.domain.Course;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Course selectCourseById(Integer courseId) {  
        return courseMapper.selectCourseById(courseId);
    }
	public List<Course> selectCourseByChain(Integer chainId) {
		// TODO Auto-generated method stub
		return courseMapper.selectCourseByChain(chainId);
	}
	public List<String> selectCoursenameByChain(Integer chainId) {
        // TODO Auto-generated method stub
        return courseMapper.selectCoursenameByChain(chainId);
    }
}
