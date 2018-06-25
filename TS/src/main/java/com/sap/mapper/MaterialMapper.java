package com.sap.mapper;

import com.sap.domain.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialMapper {

    @Select("SELECT materialinfo.* FROM materialinfo \n" +
            "         WHERE materialinfo.courseid = #{courseId}")
    List<Material> selectMaterialByCourse(@Param("courseId") int courseId);

}
