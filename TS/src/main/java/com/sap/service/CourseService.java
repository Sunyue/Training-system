package com.sap.service;

import com.github.pagehelper.PageInfo;
import com.sap.domain.Course;

import java.util.List;

public interface CourseService {
	Course selectCourseById(Integer courseId);
	PageInfo<Course> selectCourseByChain(Integer chainId, int start, int limit);
	PageInfo<String> selectCoursenameByChain(Integer chainId, int start, int limit);
	Integer createCourse(Integer chainId, String courseName, String courseDescription);
	Integer getCourseIdByName(String courseName);
	void addExistingCourse(Integer chainId, List<Integer> courseIds);
}
