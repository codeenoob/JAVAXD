package org.jing.core.logging.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.lang.JingException;
import org.jing.core.lang.annotation.Property;
import org.jing.core.logging.AbstractLog4jFilter;
import org.jing.core.util.GenericUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-31 <br>
 */
public class Log4jSqlFilter extends AbstractLog4jFilter {
    @Property("regex")
    private static String regex = null;

    @Override public boolean execute(LoggingEvent loggingEvent) throws JingException {
        if (GenericUtil.ifEmpty(loggingEvent.getLoggerName()).matches(regex)) {
            try {
                GenericUtil.setByForce(loggingEvent, "level", Level.ERROR);
                GenericUtil.setByForce(loggingEvent, "message", "???3");
                GenericUtil.setByForce(loggingEvent, "renderedMessage", "???4");
            }
            catch (JingException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
