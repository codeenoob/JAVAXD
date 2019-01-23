package org.jing.core.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;
import org.jing.core.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.HashSet;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-18 <br>
 */
public class SessionFactory {
    private static volatile SessionFactory ourInstance = null;

    private static ThreadLocal<SqlSession> threadSession = new ThreadLocal<>();

    private static final HashSet<Class<?>> mapperSet = new HashSet<>();

    private SqlSessionFactory sqlSessionFactory;

    public static SessionFactory getInstance() throws JingException {
        if (null == ourInstance) {
            synchronized (SessionFactory.class) {
                if (null == ourInstance) {
                    ourInstance = new SessionFactory();
                }
            }
        }
        return ourInstance;
    }

    private SessionFactory() throws JingException {
        try {
            StringReader reader = new StringReader("");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(new StringReader(FileUtil.readResource(Configuration.getInstance().getProperty("Mybatis"), false)));
        }
        catch (Exception e) {
            ExceptionHandler.publish("SESSION-00000", "Failed to initialize sessionFactory", e);
        }
    }

    public void registerMapper(Class<?> mapper) {
        if (!sqlSessionFactory.getConfiguration().hasMapper(mapper)) {
            sqlSessionFactory.getConfiguration().addMapper(mapper);
        }
    }

    public SqlSession getSession() {
        SqlSession session = threadSession.get();
        if (null == session) {
            session = sqlSessionFactory.openSession();
            threadSession.set(session);
        }
        return session;
    }
}
