package com.sap.service;


import java.util.List;

import com.sap.domain.Course;

public interface CourseService {
	Course selectCourseById(Integer courseId);
	List<Course> selectCourseByChain(Integer chainId);
	List<String> selectCoursenameByChain(Integer chainId);
}
