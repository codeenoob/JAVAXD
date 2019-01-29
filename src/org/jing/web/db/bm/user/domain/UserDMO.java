package org.jing.web.db.bm.user.domain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.jing.core.lang.Const;
import org.jing.core.lang.JingException;
import org.jing.core.util.DateUtil;
import org.jing.core.util.StringUtil;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.util.api.CommonUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
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
}
