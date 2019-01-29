package org.jing.core.util;

import org.apache.log4j.Logger;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-29 <br>
 */
public class DMOUtil {
    private static Logger logger = Logger.getLogger(DMOUtil.class);

    public static String fillWithDateTime(String arg) {
        return new StringBuilder("#{").append(arg).append(", typeHandler = org.jing.core.db.SqlDateTime2JavaStringTypeHandler}").toString();
    }

}
