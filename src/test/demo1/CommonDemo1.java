package test.demo1;

import init.Initialization;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.logging.Log4jFilter;
import org.jing.core.util.GenericUtil;

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
        Logger logger = Logger.getLogger(CommonDemo1.class);
        x = 2;
        GenericUtil.setByForce(this, "x", 2);
    }
    public static void main(String[] args) throws Exception {
        new CommonDemo1();
    }
}
