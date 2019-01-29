package org.jing.web.db.object.item.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jing.web.db.bm.user.domain.UserDMO;
import org.jing.web.db.bm.user.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface ItemMapper {
    // @Insert("INSERT INTO TB_USER (ID, NAME, SEX, AGE) VALUES (#{id}, #{name}, #{sex}, #{age})")
    // int saveUser(UserDto user);

    @Select("SELECT * FROM BM_USER FOR UPDATE NOWAIT")
    List<HashMap<String, String>> selectTry();

    @InsertProvider(type = UserDMO.class, method = "saveUser")
    void saveUser(@Param("user") UserDto userDto);
}
