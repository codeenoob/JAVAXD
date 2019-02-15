package init;

import org.jing.core.lang.InitFactory;
import org.jing.core.lang.JingException;
import org.jing.core.logging.Log4jInit;

/**
 * Description: <br>
 *     用来进行统一初始化. <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class Initialization {
    private static volatile Initialization ourInstance = null;

    public static Initialization getInstance() {
        if (null == ourInstance) {
            synchronized (Initialization.class) {
                if (null == ourInstance) {
                    ourInstance = new Initialization();
                }
            }
        }
        return ourInstance;
    }

    private Initialization() {
        try {
            // 1. 加载日志框架.
            initLogger();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLogger() throws JingException {
        // 目前暂时只加载Log4j框架.
        InitFactory.register(Log4jInit.class);
    }
}
