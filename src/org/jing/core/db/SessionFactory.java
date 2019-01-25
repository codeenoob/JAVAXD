package org.jing.core.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;
import org.jing.core.util.FileUtil;

import java.io.StringReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-18 <br>
 */
public class SessionFactory {
    private static volatile SessionFactory ourInstance = null;

    private static ThreadLocal<Stack<SqlSession>> threadSessionStack = new ThreadLocal<>();

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
        return getSession(false);
    }

    public SqlSession getSession(boolean autoCommit) {
        Stack<SqlSession> stack = threadSessionStack.get();
        if (null == stack) {
            stack = new Stack<>();
            threadSessionStack.set(stack);
            SqlSession session = newSession(autoCommit);
            return session;
        }
        return stack.peek();
    }

    public SqlSession newSession() {
        return newSession(false);
    }

    public SqlSession newSession(boolean autoCommit) {
        Stack<SqlSession> stack = threadSessionStack.get();
        if (null == stack) {
            stack = new Stack<>();
            threadSessionStack.set(stack);
        }
        SqlSession session = sqlSessionFactory.openSession(autoCommit);
        stack.push(session);
        return session;
    }

    public void closeSession(SqlSession session) throws JingException {
        Stack<SqlSession> stack = threadSessionStack.get();
        if (null == stack) {
            return;
        }
        SqlSession session$ = stack.pop();
        int flag$ = 0;
        while (flag$ < 2) {
            try {
                session$.commit();
                session$.close();
            }
            catch (Throwable t) {
                ExceptionHandler.publish("SESSION-00002", "Failed to close session", t);
            }
            try {
                if (0 == flag$) {
                    session$ = stack.pop();
                }
            }
            catch (EmptyStackException e) {
                if (null != session) {
                    ExceptionHandler.publish("SESSION-00001", "Cannot match the session when try to close it", e);
                }
                else {
                    return;
                }
            }
            if (session == session$ || flag$ != 0) {
                flag$++;
            }
        }
    }

    public void closeSession() throws JingException {
        closeSession(null);
    }
}
