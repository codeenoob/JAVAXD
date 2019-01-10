package test.demo1;

import init.Initialization;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jing.core.lang.JingException;
import org.jing.core.util.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class Demo1 {
    public Demo1() throws Exception {
        Initialization.getInstance();
        System.out.println(FileUtil.readFile("E:\\W\\WorkSpace\\idea\\Jing\\web\\WEB-INF\\config\\log4j.properties"));
        /*Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
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
