package com.sap.service;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sap.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public PageInfo<Course> selectCourseByChain(Integer chainId, int start, int limit) {
		// TODO Auto-generated method stub
        PageHelper.startPage(start, limit);
        List<Course> list = courseMapper.selectCourseByChain(chainId);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}

    public PageInfo<String> selectCoursenameByChain(Integer chainId, int start, int limit){
        PageHelper.startPage(start, limit);
        List<String> list =  courseMapper.selectCoursenameByChain(chainId);
        PageInfo<String> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer createCourse(Integer chainId, String courseName, String courseDescription){
        Integer result = 0;
        try{
            if(courseMapper.createCourse(courseName, courseDescription) == 1) {
                int newCourseId = courseMapper.getCourseIdByName(courseName);

                int newOrder = 0;
                try {
                    newOrder = courseMapper.getMaxSeqOrderbyChainId(chainId) + 1;
                }
                catch(Exception e) {
                    newOrder = 1;
                }

                if(courseMapper.insertcourseRelation(chainId, newCourseId, newOrder) == 1) {
                    result = 1;
                }
            }
        }catch (Exception e){
            return 0;
        }
        return result;
    }

    @Override
    public Integer getCourseIdByName(String courseName){
        return courseMapper.getCourseIdByName(courseName);
    }
}
