package org.jing.core.logging;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.JingException;
import org.jing.core.lang.Pair2;
import org.jing.core.lang.annotation.Property;
import org.jing.core.logging.impl.Log4jSqlFilter;
import org.jing.core.util.GenericUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-30 <br>
 */
public class Log4jFilter extends Filter {
    private static Logger logger = Logger.getLogger(Log4jFilter.class);

    private static HashMap<Class<? extends AbstractLog4jFilter>, String> LOCAL_FILTER_CLASS_MAP = null;

    private static List<AbstractLog4jFilter> LOCAL_FILTER_LIST = null;

    public Log4jFilter() throws JingException {
        HashMap<Object, Object> propertyMap = null;
        // 从system.cfg里读取Logger.log4j.extendFilter开头的配置
        if (null == LOCAL_FILTER_CLASS_MAP) {
            synchronized (Log4jFilter.class) {
                if (null == LOCAL_FILTER_CLASS_MAP) {
                    LOCAL_FILTER_CLASS_MAP = new HashMap<>();
                    if (null == propertyMap) {
                        propertyMap = Configuration.getInstance().getPropertyMap();
                    }
                    for (Map.Entry<Object, Object> entry: propertyMap.entrySet()) {
                        if (String.valueOf(entry.getKey()).matches("Logger.log4j.extendFilter\\.[^\\.]*?")) {
                            try {
                                Class<? extends AbstractLog4jFilter> filterClass$ =
                                    (Class<? extends AbstractLog4jFilter>) Class.forName(String.valueOf(entry.getValue()));
                                LOCAL_FILTER_CLASS_MAP.put(filterClass$, String.valueOf(entry.getKey()));
                            }
                            catch (Exception e) {
                                System.err.println("JingException: [LOGGING-LOG4J],Failed to implement the filter in system.cfg");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        if (null == LOCAL_FILTER_LIST) {
            synchronized (Log4jFilter.class) {
                if (null == LOCAL_FILTER_LIST) {
                    LOCAL_FILTER_LIST = new ArrayList<>();
                    if (null != LOCAL_FILTER_CLASS_MAP) {
                        if (null == propertyMap) {
                            propertyMap = Configuration.getInstance().getPropertyMap();
                        }
                        for (Map.Entry<Class<? extends AbstractLog4jFilter>, String> entry : LOCAL_FILTER_CLASS_MAP.entrySet()) {
                            try {
                                Class<? extends AbstractLog4jFilter> filter$ = entry.getKey();
                                AbstractLog4jFilter filterInstance$ = filter$.newInstance();
                                String prefix$ = new StringBuilder(entry.getValue()).append(".").toString();
                                HashMap<String, String> propertyMap$ = new HashMap<>();
                                for (Map.Entry<Object, Object> entry$: propertyMap.entrySet()) {
                                    String name$ = String.valueOf(entry$.getKey());
                                    if (name$.startsWith(prefix$)) {
                                        propertyMap$.put(name$.substring(prefix$.length()), String.valueOf(entry$.getValue()));
                                    }
                                }
                                Field[] fields$ = filter$.getDeclaredFields();
                                int count$ = GenericUtil.countArray(fields$);
                                for (int i$ = 0; i$ < count$; i$++) {
                                    Property property = fields$[i$].getAnnotation(Property.class);
                                    if (null != property && fields$[i$].getType().getName().equals(String.class.getName())) {
                                        fields$[i$].setAccessible(true);
                                        fields$[i$].set(filterInstance$, GenericUtil.getString(propertyMap$, property.value()));
                                    }
                                }
                                LOCAL_FILTER_LIST.add(filterInstance$);
                            }
                            catch (Exception e) {
                                System.err.println("JingException: [LOGGING-LOG4J],Failed to implement the filter in system.cfg");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override public int decide(LoggingEvent loggingEvent) {
        int count = GenericUtil.countList(LOCAL_FILTER_LIST);
        for (int i$ = 0; i$ < count; i$++) {
            try {
                if (!LOCAL_FILTER_LIST.get(0).execute(loggingEvent)) {
                    return -1;
                }
            }
            catch (JingException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


}
