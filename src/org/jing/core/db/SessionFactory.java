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
import java.util.Objects;
import java.util.Stack;

/**
 * Description: <br>
 *     Session工厂. <br>
 *     采用单例模式. <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-18 <br>
 */
@SuppressWarnings({ "WeakerAccess", "unused", "ConstantConditions" })
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
            // 读取system.properties指定的mybatis配置文件.
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(new StringReader(Objects
                .requireNonNull(FileUtil.readResource(Configuration.getInstance().getProperty("Mybatis"), false))));
        }
        catch (Exception e) {
            ExceptionHandler.publish("SESSION-00000", "Failed to initialize sessionFactory", e);
        }
    }

    public void registerMapper(Class<?> mapper) {
        // 注册mapper.
        if (!sqlSessionFactory.getConfiguration().hasMapper(mapper)) {
            synchronized (SessionFactory.class) {
                if (!sqlSessionFactory.getConfiguration().hasMapper(mapper)) {
                    sqlSessionFactory.getConfiguration().addMapper(mapper);
                }
            }
        }
    }

    /**
     * Description: <br>
     *     通过不自动提交的方式获得一个session. <br>
     *
     * @author: bks <br>
     * @return org.apache.ibatis.session.SqlSession <br>
     */
    public SqlSession getSession() {
        return getSession(false);
    }

    /**
     * Description:  <br>
     *     根据入参决定获得一个是否自动提交的session. <br>
     *     假如当前线程的sessionStack是空的, 则创建一个新的session. <br>
     *     假如当前现成的sessionStack非空的, 则获取最新的有效session. <br>
     *
     * @author: bks <br>
     * @param autoCommit 是否自动提交 <br>
     * @return org.apache.ibatis.session.SqlSession <br>
     */
    public SqlSession getSession(boolean autoCommit) {
        Stack<SqlSession> stack = threadSessionStack.get();
        if (null == stack) {
            stack = new Stack<>();
            threadSessionStack.set(stack);
            return newSession(autoCommit);
        }
        return stack.peek();
    }

    /**
     * Description: <br>
     *     通过不自动提交的方式新建一个session放入当前线程sessionStack里并返回. <br>
     *
     * @author: bks <br>
     * @return org.apache.ibatis.session.SqlSession <br>
     */
    public SqlSession newSession() {
        return newSession(false);
    }


    /**
     * Description:  <br>
     *     根据入参决定新建一个是否自动提交的session放入当前线程sessionStack里并返回. <br>
     *
     * @author: bks <br>
     * @param autoCommit 是否自动提交 <br>
     * @return org.apache.ibatis.session.SqlSession <br>
     */
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
