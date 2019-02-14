package org.jing.core.logging.log4j;

import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.lang.JingException;
import org.jing.core.util.GenericUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-14 <br>
 */
public class Log4jClassifyPlugin {
    public void execute(LoggingEvent loggingEvent) throws JingException {
        if (loggingEvent.getLoggerName().matches("org.jing..+?.mapper.[a-zA-Z]+?Mapper.[a-zA-Z]+?")) {
            GenericUtil.setByForce(loggingEvent, "level", Log4jLoggerLevel.SQL);
        }
    }
}
