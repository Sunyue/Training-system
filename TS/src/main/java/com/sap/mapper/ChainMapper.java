package com.sap.mapper;

import com.sap.domain.Chain;
import com.sap.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChainMapper {

    @Select("SELECT * FROM chaininfo")
    List<Chain> selectAllChain();

    @Select("SELECT chaininfo.* FROM chaininfo, userchain \n" +
            "         WHERE chaininfo.chainid = userchain.chainid\n" +
            "           AND userchain.username = #{username}")
    List<Chain> selectChainByUser(@Param("username") String username);
}
