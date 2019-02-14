package org.jing.core.logging.log4j.impl;

import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.db.SessionFactory;
import org.jing.core.lang.JingException;
import org.jing.core.lang.annotation.Property;
import org.jing.core.logging.log4j.AbstractLog4jFilter;
import org.jing.core.util.GenericUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-31 <br>
 */
public class Log4jSqlFilter extends AbstractLog4jFilter {

    @Override
    public boolean execute(LoggingEvent loggingEvent) {
        try {
            StringBuilder stbr = new StringBuilder("[Session: ")
                .append(SessionFactory.getInstance().getSession().getConnection().hashCode()).append("]\r\n")
                .append(GenericUtil.getByForce(loggingEvent, "message"));
            GenericUtil.setByForce(loggingEvent, "renderedMessage", stbr.toString());
            return true;
        }
        catch (JingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
