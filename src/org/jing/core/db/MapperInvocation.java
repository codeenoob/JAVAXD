package org.jing.core.db;

import org.apache.log4j.Logger;
import org.jing.core.lang.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-24 <br>
 */
public class MapperInvocation implements InvocationHandler {
    private static Logger logger = Logger.getLogger(MapperFactory.class);

    private Object target;

    public Object bind(Object object) {
        target = object;
        Object warpedItf = Proxy.newProxyInstance(target.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
        return warpedItf;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("MapperInvocation start..." + target.getClass() + "->>-" + method.getName());
        try {
            method.invoke(target, args);
        }
        catch (Throwable t) {
            try {
                ExceptionHandler.publish("JDBC-0000", "Unexpected error occurred.", t);
            }
            catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                logger.error(sw.toString());
            }
        }
        logger.info("MapperInvocation end..." + target.getClass() + "->>-" + method.getName());
        return null;
    }
}
