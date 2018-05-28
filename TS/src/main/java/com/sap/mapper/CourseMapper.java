package com.sap.mapper;

import com.sap.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("SELECT * FROM courseinfo WHERE courseid = #{courseId}")
    Course selectCourseById(@Param("courseId") int courseId);

    @Select("SELECT courseinfo.* FROM courseinfo, chaininfo, chaincourse \n" +
            "         WHERE chaininfo.chainid = chaincourse.chainid\n" +
            "           AND courseinfo.courseid = chaincourse.courseid\n" +
            "           AND chaininfo.chainid = #{chainId}")
    List<Course> selectCourseByChain(@Param("chainId") int chainId);

    @Select("SELECT courseinfo.coursename FROM courseinfo, chaininfo, chaincourse \n" +
            "         WHERE chaininfo.chainid = chaincourse.chainid\n" +
            "           AND courseinfo.courseid = chaincourse.courseid\n" +
            "           AND chaininfo.chainid = #{chainId}")
    List<String> selectCoursenameByChain(@Param("chainId") int chainId);
}
