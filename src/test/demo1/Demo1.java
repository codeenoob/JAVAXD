package test.demo1;

import init.Initialization;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jing.core.db.MapperFactory;
import org.jing.core.db.SessionFactory;
import org.jing.core.lang.Configuration;
import org.jing.core.lang.JingException;
import org.jing.core.mapper.UserMapper;
import org.jing.core.util.FileUtil;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class Demo1 {
    Logger logger = Logger.getLogger(Demo1.class);

    public Demo1() throws Exception {
        Initialization.getInstance();
        // System.out.println(FileUtil.readFile("E:\\W\\WorkSpace\\idea\\Jing\\web\\WEB-INF\\config\\log4j.properties"));

        logger.info(Configuration.getInstance().getLocalPropertyMap());
        logger.info(Configuration.getInstance().getPropertyMap());
        logger.info("==========[Split]=============");
        UserMapper mapper = MapperFactory.getMapper(UserMapper.class);
        logger.info(mapper.selectTry());
        /*
        BufferedReader br = new BufferedReader(reader);
        System.out.println(br.readLine());*/
        /*SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        session.commit();
        session.close();*/
    }

    public static void main(String[] args) throws Exception {
        new Demo1();
    }
}
