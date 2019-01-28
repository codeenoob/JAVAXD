package test.demo1;

import init.Initialization;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
import org.jing.core.db.SessionFactory;
import org.jing.core.lang.Configuration;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.bm.user.mapper.UserMapper;

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
        mapper.selectTry();
        // SessionFactory.getInstance().closeSession();
        SqlSession session = SessionFactory.getInstance().newSession();
        UserMapper mapper$ = MapperFactory.getMapper(session, UserMapper.class);
        mapper$.selectTry();
        System.out.println("???");
        mapper$.selectTry();
        System.out.println("??????");
        // UserDto userDto = new UserDto();
        // userDto.setId();
        logger.info(mapper$.selectTry());
        System.out.println("?????????");

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
