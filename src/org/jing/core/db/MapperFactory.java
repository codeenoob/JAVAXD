package org.jing.core.db;

import org.apache.ibatis.session.SqlSession;
import org.jing.core.lang.JingException;

/**
 * Description: <br>
 *     映射工厂. 所有的映射关系都应该从这里创建, 这样才能得到很好的控制. <br>
 *     包括在与数据库的连接池中注册该mapper. 如果不使用这个工厂, 很可能出现映射关系未注册. <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-23 <br>
 */
@SuppressWarnings({"WeakerAccess", "unchecked"})
public class MapperFactory {
    public static void registerMapper(Class<?> mapper) throws JingException {
        SessionFactory.getInstance().registerMapper(mapper);
    }

    /**
     * Description:  <br>
     *     为传入的session创建一个映射关系. <br>
     *
     * @author: bks <br>
     * @param session 指定创建映射关系的session, 如果是null, 则默认获得当前线程最新的session. <br>
     * @param mapper 指定要创建的映射关系. <br>
     * @return T <br>
     * @throws JingException <br>
     */
    public static <T> T getMapper(SqlSession session, Class<T> mapper) throws JingException {
        if (null == session) {
            SessionFactory.getInstance().registerMapper(mapper);
            session = SessionFactory.getInstance().getSession();
        }
        MapperInvocation pxy = new MapperInvocation();
        T itf = session.getMapper(mapper);
        return (T) pxy.bind(itf);
    }

    /**
     * Description:  <br>
     *     为当前线程最新的session创建一个映射关系. <br>
     *
     * @author: bks <br>
     * @param mapper 指定要创建的映射关系. <br>
     * @return T <br>
     * @throws JingException <br>
     */
    public static <T> T getMapper(Class<T> mapper) throws JingException {
        return getMapper(null, mapper);
    }
}
