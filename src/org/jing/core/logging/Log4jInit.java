package org.jing.core.logging;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Filter;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.InitFactory;
import org.jing.core.lang.JInit;
import org.jing.core.lang.JingException;
import org.jing.core.logging.log4j.Log4jLoggerLevel;
import org.jing.core.util.FileUtil;
import org.jing.core.util.GenericUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashSet;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-14 <br>
 */
public class Log4jInit implements JInit, Serializable {
    @Override
    public void init() throws JingException {
        PropertyConfigurator.configure(FileUtil.readProperties(Configuration.getInstance().getProperty("Logger"), false));
        try {
            // 2.1. 加载特殊日志记录级别.
            registerLoggerLevel();
            // 2.2. 绑定Appender.
            bindAppender();
            // 2.3. 加载过滤器.
            bindFilter();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void bindFilter() throws Exception {
        Class<?> filterClass = Class.forName(Configuration.getInstance().getProperty("Logger.log4j.rootFilter"));
        Enumeration es = Logger.getRootLogger().getAllAppenders();
        Filter filter = (Filter) InitFactory.register((Class<? extends JInit>) filterClass);
        while (es.hasMoreElements()) {
            ((Appender) es.nextElement()).addFilter(filter);
        }
    }

    private void bindAppender() {
        Logger rootLogger = Logger.getRootLogger();
        Enumeration e = rootLogger.getAllAppenders();
        while (e.hasMoreElements()) {
            AppenderSkeleton appender$ = (AppenderSkeleton) e.nextElement();
            Level level$ = Log4jLoggerLevel.LEVEL_MAPPING.get(appender$.getName().toUpperCase());
            if (null != level$) {
                appender$.setThreshold(level$);
            }
        }
    }

    private void registerLoggerLevel() throws Exception {
        Field[] fields$1 = Log4jLoggerLevel.class.getDeclaredFields();
        int count$fields$1 = fields$1.length;
        Field[] fields$2 = Level.class.getDeclaredFields();
        int count$fields$2= fields$2.length;
        Field[] fields = new Field[count$fields$1 + count$fields$2];
        System.arraycopy(fields$1, 0, fields, 0, count$fields$1);
        System.arraycopy(fields$2, 0, fields, count$fields$1, count$fields$2);
        for (Field field$ : fields) {
            if (Modifier.isStatic(field$.getModifiers()) && field$.getType() == Level.class) {
                Log4jLoggerLevel.LEVEL_MAPPING.put(field$.getName(), (Level) field$.get(null));
            }
        }
        String equals = GenericUtil.ifEmpty(Configuration.getInstance().getProperty("Logger.log4j.level.equals"));
        registerPriority(equals, Log4jLoggerLevel.EQUALS_PRIORITY);
        String gore = GenericUtil.ifEmpty(Configuration.getInstance().getProperty("Logger.log4j.level.gore"));
        registerPriority(gore, Log4jLoggerLevel.GORE_PRIORITY);
        String ignore = GenericUtil.ifEmpty(Configuration.getInstance().getProperty("Logger.log4j.level.gore.ignore"));
        registerPriority(ignore, Log4jLoggerLevel.IGNORE_PRIORITY);
    }

    private static void registerPriority(String property, HashSet<Level> hashSet) {
        String[] levels = property.split(",");
        for (String level$ : levels) {
            Level l$ = Log4jLoggerLevel.LEVEL_MAPPING.get(level$);
            if (null != l$) {
                hashSet.add(l$);
            }
        }
    }
}
