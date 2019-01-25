package org.jing.core.db;

import org.apache.ibatis.session.SqlSession;
import org.jing.core.lang.JingException;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-23 <br>
 */
public class MapperFactory {
    public static <T> T getMapper(SqlSession session, Class<T> mapper) throws JingException {
        if (null == session) {
            SessionFactory.getInstance().registerMapper(mapper);
            session = SessionFactory.getInstance().getSession();
        }
        MapperInvocation pxy = new MapperInvocation();
        T itf = session.getMapper(mapper);
        T itf$ = (T) pxy.bind(itf);
        return itf$;
    }

    public static <T> T getMapper(Class<T> mapper) throws JingException {
        return getMapper(null, mapper);
    }
}
