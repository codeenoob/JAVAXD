package init;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Filter;
import org.jing.core.db.SessionFactory;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.JingException;
import org.jing.core.logging.Log4jFilter;
import org.jing.core.util.FileUtil;

import java.util.Enumeration;

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
        try {
            initLogger();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLogger() throws JingException {
        PropertyConfigurator.configure(FileUtil.readProperties(Configuration.getInstance().getProperty("Logger"), false));
        try {
            Class<? extends Filter> filterClass =
                (Class<? extends Filter>) Class.forName(Configuration.getInstance().getProperty("Logger.log4j.rootFilter"));
            Enumeration es = Logger.getRootLogger().getAllAppenders();
            Filter filter = filterClass.newInstance();
            while (es.hasMoreElements()) {
                ((Appender) es.nextElement()).addFilter(filter);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        };
    }
}
