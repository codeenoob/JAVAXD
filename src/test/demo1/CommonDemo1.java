package test.demo1;

import init.Initialization;
import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jing.core.logging.Log4jLoggerLevel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-21 <br>
 */
public class CommonDemo1 {
    private transient int x = 1;
    private CommonDemo1() throws Exception {
        Log4jLoggerLevel.init();
        System.out.println(Log4jLoggerLevel.EQUALS_PRIORITY);
        System.out.println(Log4jLoggerLevel.GORE_PRIORITY);
        System.out.println(Log4jLoggerLevel.EQUALS_PRIORITY);
        /*Initialization.getInstance();
        Logger logger = Logger.getLogger(CommonDemo1.class);
        Logger.getRootLogger().setLevel(Level.ALL);
        Logger rootLogger = Logger.getRootLogger();
        Enumeration e = rootLogger.getAllAppenders();
        e.nextElement();
        e.nextElement();
        e.nextElement();
        e.nextElement();
        e.nextElement();
        DailyRollingFileAppender a = (DailyRollingFileAppender) e.nextElement();
        a.setThreshold(Log4jLoggerLevel.SQL);


        logger.log(Log4jLoggerLevel.SQL, "123123");
        logger.error("234234");*/
    }
    public static void main(String[] args) throws Exception {
        new CommonDemo1();
    }
}
