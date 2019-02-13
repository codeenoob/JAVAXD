package org.jing.core.logging;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.jing.core.lang.Configuration;
import org.jing.core.util.GenericUtil;
import org.jing.core.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-13 <br>
 */
public class Log4jDailyRollingFileAppender extends DailyRollingFileAppender {
    private static boolean initEquals = false;

    public static volatile Priority GLOBAL_PRIORITY = null;

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        boolean flag = null != priority && priority.equals(this.threshold);
        System.out.println(priority.toString());
        return flag;
    }


}
