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
            VALUES("NAME, ACCOUNT, PASSWORD, ROLE, SEX, STATE, CRT_DATE, LAST_OPER_DATE",
                "#{user.name}, #{user.account}, #{user.password}, #{user.role}, #{user.sex}, #{user.state}, "
                    + "TO_DATE(#{user.crtDate}, 'YYYY-MM-DD HH24:MI:SS'), "
                    + "TO_DATE(#{user.lastOperDate}, 'YYYY-MM-DD HH24:MI:SS')");
            String id$ = -2 != user.getId() ? "#{user.id}" : String.valueOf(CommonUtil.qrySeqNextValueByName("S_USER_ID"));
            if (StringUtil.isEmpty(user.getCrtDate())) {
                user.setCrtDate(CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME));
            }
            if (StringUtil.isEmpty(user.getLastOperDate())) {
                user.setLastOperDate(user.getCrtDate());
            }
            if (StringUtil.isEmpty(user.getState())) {
                user.setState(Const.STATE_INACTIVE);
            }
            VALUES("ID", id$);
            if (!StringUtil.isEmpty(user.getLastLoginDate())) {
                VALUES("LAST_LOGIN_DATE", "TO_DATE(#{user.lastLoginDate}, 'YYYY-MM-DD HH24:MI:SS')");
            }
            if (!StringUtil.isEmpty(user.getLastLoginDate())) {
                VALUES("LAST_LOGOUT_DATE", "TO_DATE(#{user.lastLogoutDate}, 'YYYY-MM-DD HH24:MI:SS')");
            }
        }};
        return sql.toString();
    }
}
