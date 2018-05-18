package com.sap.mapper;

import com.sap.domain.Chain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChainMapper {

    @Select("SELECT * FROM chaininfo")
    List<Chain> selectAllChain();
}
