package org.jing.core.logging;

import org.apache.log4j.Level;
import org.jing.core.lang.Configuration;
import org.jing.core.util.GenericUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
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

    private static boolean init = false;

    public static HashSet<Level> EQUALS_PRIORITY = new HashSet<>();
    public static HashSet<Level> GORE_PRIORITY = new HashSet<>();

    public static final HashMap<String, Field> levelMapping = new HashMap<>();

    public static void init() {
        if (!init) {
            synchronized (Log4jLoggerLevel.class) {
                if (!init) {
                    Field[] fields$1 = Log4jLoggerLevel.class.getDeclaredFields();
                    int count$fields$1 = fields$1.length;
                    Field[] fields$2 = Level.class.getDeclaredFields();
                    int count$fields$2= fields$2.length;
                    Field[] fields = new Field[count$fields$1 + count$fields$2];
                    System.arraycopy(fields$1, 0, fields, 0, count$fields$1);
                    System.arraycopy(fields$2, 0, fields, count$fields$1, count$fields$2);
                    int count = fields$2.length;
                    HashMap<String, Field> fieldMap = new HashMap<>();
                    for (int i$ = 0; i$ < count; i$++) {
                        Field field$ = fields[i$];
                        if (Modifier.isStatic(field$.getModifiers()) && field$.getType() == Level.class) {
                            levelMapping.put(field$.getName(), field$);
                        }
                    }
                    String equals = GenericUtil.ifEmpty(Configuration.getInstance().getProperty("Logger.log4j.level.equals"));
                    registerPriority(equals, EQUALS_PRIORITY);
                    String gore = GenericUtil.ifEmpty(Configuration.getInstance().getProperty("Logger.log4j.level.gore"));
                    registerPriority(gore, GORE_PRIORITY);
                    init = true;
                }
            }
        }
    }

    protected Log4jLoggerLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }

    private static void registerPriority(String property, HashSet<Level> hashSet) {
        String[] levels = property.split(",");
        int count = null == levels ? 0 : levels.length;
        for (int i$ = 0; i$ < count; i$++) {
            String level$ = levels[i$];
            Field field$ = Log4jLoggerLevel.levelMapping.get(level$);
            if (null != field$) {
                try {
                    Level l$ = (Level) field$.get(null);
                    hashSet.add(l$);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
