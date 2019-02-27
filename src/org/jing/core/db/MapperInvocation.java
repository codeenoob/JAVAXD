package org.jing.core.db;

import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.annotation.RegisterMapper;

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
            // 假如Mapper的方法上有@RegisterMapper注解, 则意味着需要先行注册其他Mapper.
            RegisterMapper registerMapper = method.getAnnotation(RegisterMapper.class);
            if (null != registerMapper) {
                Class<?>[] registerMappers = registerMapper.value();
                int count = null == registerMappers ? 0 : registerMappers.length;
                for (int i$ = 0; i$ < count; i$++) {
                    MapperFactory.registerMapper(registerMappers[i$]);
                }
            }
            return method.invoke(target, args);
        }
        catch (Throwable t) {
            ExceptionHandler.publish("JDBC-0000", "Unexpected error occurred.", t);
        }
        return null;
    }
}
