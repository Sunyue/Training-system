package com.sap.mapper;

import com.sap.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM userinfo WHERE username = #{username}")
    User selectUserByName(String username);
}
