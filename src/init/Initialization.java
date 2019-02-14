package init;

import org.jing.core.lang.InitFactory;
import org.jing.core.lang.JingException;
import org.jing.core.logging.Log4jInit;

/**
 * Description: <br>
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
            initLogger();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLogger() throws JingException {
        // 2. 加载日志框架
        InitFactory.register(Log4jInit.class);
    }
}
