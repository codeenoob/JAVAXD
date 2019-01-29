package test.demo1;

import init.Initialization;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
import org.jing.core.db.SessionFactory;
import org.jing.core.lang.Configuration;
import org.jing.web.db.bm.user.api.User;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.bm.user.mapper.UserMapper;

import java.util.List;

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

        logger.info("==========[Split]=============");
        UserMapper mapper = MapperFactory.getMapper(UserMapper.class);
        // List<UserDto> list = mapper.selectTry("A");
        // System.out.println(list);
        mapper.saveTemp("123", "2018-01-01 21:42:43");
        SessionFactory.getInstance().closeSession();
        /*
        BufferedReader br = new BufferedReader(reader);
        System.out.println(br.readLine());*/
        /*SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        session.commit();
        session.close();*/
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        new Demo1();
    }
}
