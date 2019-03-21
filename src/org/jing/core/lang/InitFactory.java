package org.jing.core.lang;

import org.apache.log4j.Logger;
import org.jing.core.lang.itf.JInit;

import java.util.Hashtable;

/**
 * Description: <br>
 *     初始化工厂. <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-14 <br>
 */
public class InitFactory {
    private static volatile Logger logger = null;

    /**
     * 存储继承了JInit接口的类和对应实例的map. <br>
     */
    private static final Hashtable<Class<? extends JInit>, JInit> jInitMap = new Hashtable<>();

    /**
     * Description:  <br>
     *     初始化继承了JInit接口的类. <br>
     *     通过这个方式初始化的话, 在整个系统生命力只会初始化一次, 算是个伪造的单例模式. <br>
     *
     * @author: bks <br>
     * @param type <br>
     * @return org.jing.core.lang.itf.JInit <br>
     */
    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public static JInit register(Class<? extends JInit> type) {
        if (!jInitMap.containsKey(type)) {
            synchronized (type) {
                if (!jInitMap.containsKey(type)) {
                    try {
                        JInit init = type.newInstance();
                        type.getDeclaredMethod("init").invoke(init);
                        jInitMap.put(type, init);
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

    /**
     * Description:  <br>
     *     获取继承了JInit接口的类的实例. <br>
     *     假如缓存里没有该实例, 直接初始化一个并存入缓存里. <br>
     *
     * @author: bks <br>
     * @param type <br>
     * @return org.jing.core.lang.itf.JInit <br>
     */
    public static JInit getInstance(Class<? extends JInit> type) {
        if (!jInitMap.containsKey(type)) {
            synchronized (InitFactory.class) {
                if (!jInitMap.containsKey(type)) {
                    return register(type);
                }
            }
        }
        log("Success to get class: " + type.getName());
        return jInitMap.get(type);
    }

    /**
     * Description:  <br>
     *     特殊的日志方法, 因为考虑到日志框架也是依靠这个工厂进行初始化. <br>
     *
     * @author: bks <br>
     * @param msg <br>
     */
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
