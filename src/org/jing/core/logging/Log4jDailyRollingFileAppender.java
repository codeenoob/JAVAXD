package org.jing.core.logging;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
import org.jing.core.lang.Configuration;
import org.jing.core.util.GenericUtil;
import org.jing.core.util.StringUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-13 <br>
 */
public class Log4jDailyRollingFileEqualsAppender extends DailyRollingFileAppender {
    private static boolean init = false;

    public static volatile Priority GLOBAL_PRIORITY = null;

    public static Priority getGlobalPriority() {
        if (!init && null == GLOBAL_PRIORITY) {
            synchronized (Log4jDailyRollingFileEqualsAppender.class) {
                if (!init && null == GLOBAL_PRIORITY) {
                    synchronized (Log4jDailyRollingFileEqualsAppender.class) {
                        String name = Configuration.getInstance().getProperty("Logger.log4j.global.level.name");
                        int value = GenericUtil.parseInteger(Configuration.getInstance().getProperty("Logger.log4j.global.level.value"), 0);
                        int syslogEquivalent = GenericUtil.parseInteger(Configuration.getInstance().getProperty("Logger.log4j.global.level.syslogEquivalent"), 6);
                        if (StringUtil.isNotEmpty(name)) {
                            GLOBAL_PRIORITY = new Log4jLoggerLevel(value, name, syslogEquivalent);
                        }
                        init = true;
                        return GLOBAL_PRIORITY;
                    }
                }
                else {
                    return GLOBAL_PRIORITY;
                }
            }
        }
        else {
            return GLOBAL_PRIORITY;
        }
    }

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        boolean flag = null != priority && priority.equals(this.threshold);
        System.out.println(priority.toString());
        return flag;
    }
}
