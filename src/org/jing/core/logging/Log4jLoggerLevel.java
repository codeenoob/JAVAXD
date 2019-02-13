package org.jing.core.logging;

import org.apache.log4j.Level;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-03 <br>
 */
public class Log4jLoggerLevel extends Level {
    public static final Level SQL = new Log4jLoggerLevel(1, "SQL", 6);
    protected Log4jLoggerLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }
}
