package test.demo1;

import init.Initialization;
import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
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

        logger.info(Configuration.getInstance().getLocalPropertyMap());
        logger.info(Configuration.getInstance().getPropertyMap());
        logger.info("==========[Split]=============");
        UserMapper mapper = MapperFactory.getMapper(UserMapper.class);
        mapper.selectTry();
        UserDto userDto = new UserDto();
        // userDto.setId();
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
