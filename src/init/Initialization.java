package init;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.PropertyConfigurator;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.Const;
import org.jing.core.lang.JingException;
import org.jing.core.util.FileUtil;

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
            // PropertyConfigurator.configure(Resources.getResourceAsProperties("org\\jing\\cfg\\log4j.properties"));
            PropertyConfigurator.configure(FileUtil.readProperties(
                new StringBuilder("org").append(Const.SYSTEM_FILE_SEPERATOR)
                    .append("jing").append(Const.SYSTEM_FILE_SEPERATOR)
                    .append("cfg").append(Const.SYSTEM_FILE_SEPERATOR)
                    .append(Configuration.getInstance().getProperty("Logger")).toString(),
                false));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
