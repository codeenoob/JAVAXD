package org.jing.core.db;

import org.apache.log4j.Logger;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.util.StringUtil;
import org.jing.core.util.ToUtil;

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
        try {
            return method.invoke(target, args);
        }
        catch (Throwable t) {
            ExceptionHandler.publish("JDBC-0000", "Unexpected error occurred.", t);
        }
        return null;
    }
}
