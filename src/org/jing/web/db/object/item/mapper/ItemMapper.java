package org.jing.web.db.object.item.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.jing.core.db.SqlDateTime2JavaStringTypeHandler;
import org.jing.core.lang.annotation.RegisterMapper;
import org.jing.web.db.bm.user.mapper.UserMapper;
import org.jing.web.db.object.item.dto.ItemDto;

public interface ItemMapper {
    @Select("SELECT T.* FROM O_ITEM T WHERE T.ID = #{id}")
    @Results(id = "qryItemById", value = {
        @Result(property = "id", column = "ID", id = true, javaType = int.class, jdbcType = JdbcType.INTEGER),
        @Result(property = "seq", column = "SEQ", javaType = int.class, jdbcType = JdbcType.INTEGER),
        @Result(property = "crtDate", column = "CRT_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class),
        @Result(property = "crtUser", column = "CRT_USER", one = @One(select = "org.jing.web.db.bm.user.mapper.UserMapper.qryUserById", fetchType = FetchType.LAZY)),
        @Result(property = "modDate", column = "CRT_DATE", typeHandler = SqlDateTime2JavaStringTypeHandler.class),
        @Result(property = "modUser", column = "CRT_USER", one = @One(select = "org.jing.web.db.bm.user.mapper.UserMapper.qryUserById", fetchType = FetchType.LAZY))
    })
    @RegisterMapper(UserMapper.class)
    ItemDto qryItemById(@Param("id") String id);
}
