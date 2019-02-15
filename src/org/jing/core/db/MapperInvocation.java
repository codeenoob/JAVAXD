package org.jing.core.db;

import org.jing.core.lang.ExceptionHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: <br>
 *     映射关系拦截器. <br>
 *     方便未来的对mapper的管理. <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-24 <br>
 */
@SuppressWarnings("WeakerAccess")
public class MapperInvocation implements InvocationHandler {
    private Object target;

    public Object bind(Object object) {
        target = object;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
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
