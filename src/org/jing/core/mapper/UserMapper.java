package org.jing.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    // @Insert("INSERT INTO TB_USER (ID, NAME, SEX, AGE) VALUES (#{id}, #{name}, #{sex}, #{age})")
    // int saveUser(User user);

    @Select("Select * FROM TB_USER")
    List<HashMap<String, String>> selectTry();

    @Update("UPDATE TB_USER T SET T.SEX = #{sex} WHERE T.ID = #{id}")
    int updateUserByID(@Param("id") int id, @Param("sex") String sex);

    @Update("UPDATE TB_USER T SET T.SEX = #{sex} WHERE T.NAME = #{name}")
    int updateUserByName(@Param("name") int id, @Param("sex") String sex);
}
