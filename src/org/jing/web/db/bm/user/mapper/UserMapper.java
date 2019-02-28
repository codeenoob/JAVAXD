package org.jing.web.db.bm.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.jing.core.db.SqlDateTime2JavaStringTypeHandler;
import org.jing.web.db.bm.user.domain.UserDMO;
import org.jing.web.db.bm.user.dto.ModifyUserDto;
import org.jing.web.db.bm.user.dto.UserDto;

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


    @Insert("INSERT INTO TEMP_TEST (T_STRING, T_DATE) "
        + "VALUES (#{string}, #{date, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler})")
    void saveTemp(@Param("string") String tString, @Param("date") String tDate);

    @Select("SELECT T.* FROM BM_USER T WHERE T.ID = #{id}")
    @Results(id= "qryUserById", value = {
        @Result(property = "crtDate", column = "CRT_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class),
        @Result(property = "lastLoginDate", column = "LAST_LOGIN_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class),
        @Result(property = "lastLogoutDate", column = "LAST_LOGOUT_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class),
        @Result(property = "lastOperDate", column = "LAST_OPER_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class)
    })
    UserDto qryUserById(@Param("id") String id);

    @Select("SELECT T.* FROM BM_USER T WHERE UPPER(T.ACCOUNT) = UPPER(#{account})")
    @ResultMap("qryUserById")
    UserDto qryUserByAccount(@Param("account") String account);

    @InsertProvider(type = UserDMO.class, method = "saveUser")
    void saveUser(@Param("user") UserDto userDto);

    @UpdateProvider(type = UserDMO.class, method = "modifyUser")
    int modifyUserByAccountOrId(ModifyUserDto userDto);
}
