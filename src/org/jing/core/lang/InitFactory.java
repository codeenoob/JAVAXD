package org.jing.core.lang;

import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-14 <br>
 */
public class InitFactory {
    private static Logger logger = null;

    private static final HashMap<Class<? extends JInit>, JInit> map = new HashMap<>();

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public static JInit register(Class<? extends JInit> type) {
        if (!map.containsKey(type)) {
            synchronized (type) {
                if (!map.containsKey(type)) {
                    try {
                        JInit init = type.newInstance();
                        type.getDeclaredMethod("init").invoke(init);
                        map.put(type, init);
                        log("Success to register class: " + type.getName());
                        return init;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static JInit getInstance(Class<? extends JInit> type) {
        log("Success to get class: " + type.getName());
        return map.get(type);
    }

    private static void log(String msg) {
        if (null == logger) {
            synchronized (InitFactory.class) {
                if (null == logger) {
                    logger = Logger.getLogger(InitFactory.class);
                }
            }
        }
        logger.info(msg);
    }
}
