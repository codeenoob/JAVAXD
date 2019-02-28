package org.jing.web.db.bm.user.domain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.jing.core.lang.Const;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;
import org.jing.core.util.DateUtil;
import org.jing.core.util.StringUtil;
import org.jing.web.db.bm.user.dto.ModifyUserDto;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.util.api.CommonUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
@SuppressWarnings("ALL")
public class UserDMO {
    public String saveUser(@Param("user") UserDto user) throws JingException {
        SQL sql = new SQL(){{
            INSERT_INTO("BM_USER");
            VALUES("ID, NAME, ACCOUNT, PASSWORD, ROLE, SEX, STATE, "
                    + "CRT_DATE, LAST_OPER_DATE, LAST_LOGIN_DATE, LAST_LOGOUT_DATE",
                "#{user.id}, #{user.name}, #{user.account}, #{user.password}, #{user.role}, #{user.sex}, #{user.state}, "
                    + "#{user.crtDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}, "
                    + "#{user.lastOperDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}, "
                    + "#{user.lastLoginDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}, "
                    + "#{user.lastLogoutDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}");
            if (-2 == user.getId()) {
                user.setId(CommonUtil.qrySeqNextValueByName("S_USER_ID"));
            }
            if (StringUtil.isEmpty(user.getCrtDate())) {
                user.setCrtDate(CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME));
            }
            if (StringUtil.isEmpty(user.getLastOperDate())) {
                user.setLastOperDate(user.getCrtDate());
            }
            if (StringUtil.isEmpty(user.getState())) {
                user.setState(Const.STATE_INACTIVE);
            }
        }};
        return sql.toString();
    }

    public String modifyUser(@Param("user")ModifyUserDto userDto) throws JingException {
        if (-2 == userDto.getId() && StringUtil.isEmpty(userDto.getAccount())) {
            ExceptionHandler.publish("USER-0000", "Failed to update a user without ID or Account.");
        }
        SQL sql = new SQL(){{
            UPDATE("BM_USER");
            if (StringUtil.isNotEmpty(userDto.getName())) {
                SET("NAME = #{name}");
            }
            if (StringUtil.isNotEmpty(userDto.getPassword())) {
                SET("PASSWORD = #{password}");
            }
            if (-2 != userDto.getRole()) {
                SET("ROLE = #{role}");
            }
            if (-2 != userDto.getSex()) {
                SET("SEX = #{sex}");
            }
            if (StringUtil.isNotEmpty(userDto.getLastLoginDate())) {
                SET("LAST_LOGIN_DATE = #{lastLoginDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}");
            }
            if (StringUtil.isNotEmpty(userDto.getLastLogoutDate())) {
                SET("LAST_LOGOUT_DATE = #{lastLogoutDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}");
            }
            if (StringUtil.isNotEmpty(userDto.getLastOperDate())) {
                SET("LAST_OPER_DATE = #{lastOperDate, typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}");
            }
            if (StringUtil.isNotEmpty(userDto.getState())) {
                SET("STATE = #{state}");
            }
            if (-2 != userDto.getId()) {
                WHERE("ID = #{id}");
            }
            else if (StringUtil.isNotEmpty(userDto.getAccount())) {
                WHERE("ACCOUNT = #{account}");
            }
        }};
        return sql.toString();
    }
}
