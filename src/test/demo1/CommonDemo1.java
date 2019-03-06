package test.demo1;

import init.Initialization;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jing.core.lang.Configuration;
import org.jing.core.logging.log4j.Log4jLoggerLevel;

import java.lang.reflect.Field;
import java.util.Enumeration;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-21 <br>
 */
public class CommonDemo1 {
    private transient int x = 1;
    private CommonDemo1() throws Exception {
        Configuration.getInstance();
        Logger logger = Logger.getLogger(CommonDemo1.class);
        logger.log(Log4jLoggerLevel.SQL, "123123");
        logger.error("234234");
    }
    public static void main(String[] args) throws Exception {
        new CommonDemo1();
    }
}
