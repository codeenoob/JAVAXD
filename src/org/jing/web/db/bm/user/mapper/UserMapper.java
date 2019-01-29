package org.jing.web.db.bm.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.jing.core.db.SqlDateTime2JavaStringTypeHandler;
import org.jing.web.db.bm.user.domain.UserDMO;
import org.jing.web.db.bm.user.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    // @Insert("INSERT INTO TB_USER (ID, NAME, SEX, AGE) VALUES (#{id}, #{name}, #{sex}, #{age})")
    // int saveUser(UserDto user);

    @Select("SELECT * FROM BM_USER WHERE STATE = #{state}")
    @Results(id = "selectTry", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "crtDate", column = "crt_date", typeHandler = SqlDateTime2JavaStringTypeHandler.class)
    })
    List<UserDto> selectTry(@Param("state") String state);

    @InsertProvider(type = UserDMO.class, method = "saveUser")
    void saveUser(@Param("user") UserDto userDto);

    @Insert("INSERT INTO TEMP_TEST (T_STRING, T_DATE) "
        + "VALUES (#{string}, #{date, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler})")
    void saveTemp(@Param("string") String tString, @Param("date") String tDate);
}
