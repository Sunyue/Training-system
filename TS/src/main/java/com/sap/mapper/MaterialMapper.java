package com.sap.mapper;

import com.sap.domain.Material;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialMapper {

    @Select("SELECT materialinfo.* FROM materialinfo \n" +
            "         WHERE materialinfo.courseid = #{courseId}")
    List<Material> selectMaterialByCourse(@Param("courseId") int courseId);

    @Select("SELECT materialinfo.* FROM materialinfo \n" +
            "         WHERE materialinfo.materialid = #{materialId}" )
    Material selectMaterialById(@Param("materialId") int materialId);

    @Insert("Insert into materialinfo(materialname,courseid,materialtype) values \n"+
            "   (#{material.materialName},#{material.courseId},#{material.materialLearnType})")
    void addMaterial(@Param("material") Material material );

}
