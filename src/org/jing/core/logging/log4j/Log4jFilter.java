package org.jing.core.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.itf.JInit;
import org.jing.core.lang.annotation.Property;
import org.jing.core.util.GenericUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-30 <br>
 */
@SuppressWarnings("unchecked")
public class Log4jFilter extends Filter implements JInit {
    private static final HashMap<Level, AbstractLog4jFilter> LEVEL_FILTER_MAP = new HashMap<>();

    private static Log4jClassifyPlugin PLUGIN_CLASSIFY = new Log4jClassifyPlugin();

    public Log4jFilter() {
    }

    @Override public int decide(LoggingEvent loggingEvent) {
        try {
            if (null != PLUGIN_CLASSIFY) {
                PLUGIN_CLASSIFY.execute(loggingEvent);
            }
            AbstractLog4jFilter log4jFilter = LEVEL_FILTER_MAP.get(loggingEvent.getLevel());
            if (null != log4jFilter && !log4jFilter.execute(loggingEvent)) {
                return -1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void init() {
        try {
            // 从system.cfg里读取Logger.log4j.extendFilter开头的配置
            HashMap<Object, Object> propertyMap = Configuration.getInstance().getPropertyMap();
            for (Map.Entry<Object, Object> entry : propertyMap.entrySet()) {
                String propertyKey = String.valueOf(entry.getKey());
                String propertyValue = String.valueOf(entry.getValue());
                if (propertyKey.matches("Logger.log4j.extendFilter\\.[^.]*?")) {
                    Class<? extends AbstractLog4jFilter> filterClass$ = (Class<? extends AbstractLog4jFilter>) Class
                        .forName(propertyValue);
                    Level level$ = Log4jLoggerLevel.LEVEL_MAPPING.get(propertyKey.substring(26).toUpperCase());
                    if (null != level$) {
                        AbstractLog4jFilter filterInstance$ = filterClass$.newInstance();
                        String prefix$ = propertyKey + ".";
                        HashMap<String, String> propertyMap$ = new HashMap<>();
                        for (Map.Entry<Object, Object> entry$ : propertyMap.entrySet()) {
                            String name$ = String.valueOf(entry$.getKey());
                            if (name$.startsWith(prefix$)) {
                                propertyMap$.put(name$.substring(prefix$.length()), String.valueOf(entry$.getValue()));
                            }
                        }
                        Field[] fields$ = filterClass$.getDeclaredFields();
                        int count$ = GenericUtil.countArray(fields$);
                        for (int i$ = 0; i$ < count$; i$++) {
                            Property property = fields$[i$].getAnnotation(Property.class);
                            if (null != property && fields$[i$].getType().getName().equals(String.class.getName())) {
                                fields$[i$].setAccessible(true);
                                fields$[i$].set(filterInstance$, GenericUtil.getString(propertyMap$, property.value()));
                            }
                        }
                        LEVEL_FILTER_MAP.put(level$, filterInstance$);
                    }
                }
            }
        }
        catch (Exception e) {
            System.err.println("JingException: [LOGGING-LOG4J],Failed to implement the filter in system.cfg");
            e.printStackTrace();
        }
    }
}
