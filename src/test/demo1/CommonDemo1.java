package test.demo1;

import init.Initialization;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jing.core.lang.Carrier;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.Const;
import org.jing.core.lang.InitFactory;
import org.jing.core.lang.PathMapping;
import org.jing.core.lang.itf.JService;
import org.jing.core.logging.log4j.Log4jLoggerLevel;
import org.jing.core.util.CarrierUtil;
import org.jing.core.util.ClassUtil;
import org.jing.core.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-21 <br>
 */
public class CommonDemo1 {
    private transient int x = 1;
    private CommonDemo1() throws Exception {
        InitFactory.getInstance(PathMapping.class);
        Class<? super JService> clazz = PathMapping.mappingService("HELLO_WORLD");
        JService jService = (JService) clazz.newInstance();
        jService.execute(null);
    }

    public static void main(String[] args) throws Exception {
        new CommonDemo1();
    }
}
