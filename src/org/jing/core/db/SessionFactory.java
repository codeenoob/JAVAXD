package org.jing.core.db;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-18 <br>
 */
public class SessionFactory {
    private static volatile SessionFactory ourInstance = null;

    public static SessionFactory getInstance() {
        if (null == ourInstance) {
            synchronized (SessionFactory.class) {
                if (null == ourInstance) {
                    ourInstance = new SessionFactory();
                }
            }
        }
        return ourInstance;
    }

    private SessionFactory() {
    }
}
