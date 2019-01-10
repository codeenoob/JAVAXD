package init;

import org.apache.log4j.PropertyConfigurator;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class Initialization {
    private static Initialization ourInstance = new Initialization();

    public static Initialization getInstance() {
        return ourInstance;
    }

    private Initialization() {
        PropertyConfigurator.configure("E:\\W\\WorkSpace\\idea\\Jing\\web\\WEB-INF\\config\\log4j.properties");
    }
}
