package com.sap.mapper;

import com.sap.domain.Material;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MaterialMapper {

    @Select("SELECT materialinfo.* FROM materialinfo \n" +
            "         WHERE materialinfo.courseid = #{courseId}")
    List<Material> selectMaterialByCourse(@Param("courseId") int courseId);

    @Select("SELECT materialinfo.* FROM materialinfo \n" +
            "         WHERE materialinfo.materialid = #{material.materialId} and materialinfo.courseid = #{material.courseId}" )
    Material selectMaterialById(@Param("material") Material material);

    @Insert("Insert into materialinfo(materialname,courseid,filetype,attachfilepath) values \n"+
            "   (#{material.materialName},#{material.courseId},#{material.fileType},#{material.attachFilepath})")
    void addMaterial(@Param("material") Material material );

    @Delete("Delete from materialinfo where materialinfo.courseid = #{material.courseId}  \n"+
            "and materialinfo.materialid= #{material.materialId} ")
    void  deleteMaterial(@Param("material") Material material);


}
