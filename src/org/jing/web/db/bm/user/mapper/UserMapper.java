package org.jing.web.db.bm.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.jing.web.db.bm.user.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    // @Insert("INSERT INTO TB_USER (ID, NAME, SEX, AGE) VALUES (#{id}, #{name}, #{sex}, #{age})")
    // int saveUser(UserDto user);

    @Select("SELECT * FROM BM_USER FOR UPDATE NOWAIT")
    List<HashMap<String, String>> selectTry();

    @Insert("INSERT INTO BM_USER (ID, NAME, ACCOUNT, PASSWORD, ROLE, SEX, CRT_DATE, LAST_LOGIN_DATE, "
        + "LAST_OPER_DATE, STATE) VALUES (#{id}, #{name}, #{account}, #{password}, #{role}, #{sex}, TO_DATE(#{crtDate}, "
        + "'YYYY-MM-DD HH24:MI:SS'), TO_DATE(#{lastLoginDate}, 'YYYY-MM-DD HH24:MI:SS'), TO_DATE(#{lastOperDate}, "
        + "'YYYY-MM-DD HH24:MI:SS'), #{state})")
    void saveUser(UserDto userDto);
}
