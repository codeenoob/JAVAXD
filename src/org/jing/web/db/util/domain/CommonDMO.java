package org.jing.web.db.util.domain;

import org.apache.ibatis.jdbc.SQL;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class CommonDMO {
    @Deprecated
    public String qryCurrentValueByName(String name) {
        StringBuilder stbr = new StringBuilder("SELECT ").append(name).append(".CURRVAL FROM DUAL");
        return stbr.toString();
    }

    @Deprecated
    public String qryNextValueByName(String name) {
        StringBuilder stbr = new StringBuilder("SELECT ").append(name).append(".NEXTVAL FROM DUAL");
        return stbr.toString();
    }
}
