package com.sap.mapper;

import com.sap.domain.Course;
import com.sap.domain.CourseChain;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("INSERT INTO courseinfo (coursename,description,courselayer,parentcid) VALUES (#{courseName}, #{courseDescription}, 1, 0)")
    int createCourse(@Param("courseName") String courseName, @Param("courseDescription") String courseDescription);

    @Insert("INSERT INTO chaincourse (chainid,courseid,seqorder) VALUES (#{chainId}, #{courseId}, #{seqOrder})")
    int insertcourseRelation(@Param("chainId") Integer chainId, @Param("courseId") Integer courseId, @Param("seqOrder") Integer seqOrder);

    @Select("SELECT courseinfo.courseid FROM courseinfo WHERE coursename = #{courseName}")
    Integer getCourseIdByName(@Param("courseName") String courseName);

    @Select("SELECT max(chaincourse.seqorder) FROM chaincourse WHERE chaincourse.chainid = #{chainId}")
    Integer getMaxSeqOrderbyChainId(@Param("chainId") Integer chainId);


    @Insert("INSERT INTO chaincourse (chainid,courseid,seqorder) VALUES " +
            "<foreach collection=\"list\" item=\"item\" index= \"index\" separator =\",\">" +
            "(#{item.chainId}, #{item.courseId}, #{item.seqOrder}) </foreach>")
    Integer insertcourseListRelation(@Param("courseList") List<CourseChain> courseList);

}
