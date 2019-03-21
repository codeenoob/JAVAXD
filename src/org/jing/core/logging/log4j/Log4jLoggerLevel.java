package org.jing.core.logging.log4j;

import org.apache.log4j.Level;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-03 <br>
 */
public class Log4jLoggerLevel extends Level {
    public static final Level SQL = new Log4jLoggerLevel(60000, "SQL", 6);

    public static final HashSet<Level> EQUALS_PRIORITY = new HashSet<>();
    public static final HashSet<Level> GORE_PRIORITY = new HashSet<>();
    public static final HashSet<Level> IGNORE_PRIORITY = new HashSet<>();
    public static final HashMap<String, Level> LEVEL_MAPPING = new HashMap<>();

    protected Log4jLoggerLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }
}
