package org.jing.core.lang;

import init.Initialization;
import org.apache.ibatis.io.Resources;
import org.jing.core.util.StringUtil;
import org.jing.core.util.ToUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
@SuppressWarnings({ "WeakerAccess", "unused" })
public class Configuration {
    private static volatile Configuration ourInstance = null;

    private Properties properties;

    private Properties localProperties;

    public static Configuration getInstance() {
        if (null == ourInstance) {
            synchronized (Configuration.class) {
                if (null == ourInstance) {
                    ourInstance = new Configuration();
                    Initialization.getInstance();
                }
            }
        }

        return ourInstance;
    }

    private Configuration() {
        String jingHome = System.getProperty("JING_HOME");
        localProperties = System.getProperties();
        if (StringUtil.isEmpty(jingHome)) {
            jingHome = "org/jing/cfg/system.properties";
        }
        try {
            properties = Resources.getResourceAsProperties(jingHome);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getProperty(String key) {
        return getInstance().properties.getProperty(key);
    }

    public String getLocalProperty(String key) {
        return getInstance().localProperties.getProperty(key);
    }

    public HashMap<Object, Object> getPropertyMap() {
        return ToUtil.properties2HashMap(properties);
    }

    public HashMap<Object, Object> getLocalPropertyMap() {
        return ToUtil.properties2HashMap(localProperties);
    }
}
