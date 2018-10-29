package com.sap.mapper;

import com.sap.domain.Chain;
import com.sap.domain.Course;
import org.apache.ibatis.annotations.Insert;
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

    @Select("SELECT userchain.* FROM userchain \n" +
            "   WHERE userchain.username = #{username} \n" +
            "   AND userchain.chainid = #{chainid}")
    List<Chain> selectChainByUserAndChain(@Param("username") String username, @Param("chainid") Integer chainId);

    @Insert("INSERT INTO chaininfo (chainname,isdefined) VALUES (#{chainName}, 1)")
    int createChain(@Param("chainName") String chainName);

    @Select("SELECT chaininfo.chainid FROM chaininfo WHERE chainname = #{chainName}")
    Integer getChainIdByName(@Param("chainName") String chainName);
}
