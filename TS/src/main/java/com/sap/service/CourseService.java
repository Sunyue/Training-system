package com.sap.service;

import com.github.pagehelper.PageInfo;
import com.sap.domain.Course;

public interface CourseService {
	Course selectCourseById(Integer courseId);
	PageInfo<Course> selectCourseByChain(Integer chainId, int start, int limit);
	PageInfo<String> selectCoursenameByChain(Integer chainId, int start, int limit);
}
