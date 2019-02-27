package org.jing.web.db.bm.demo;

import init.Initialization;
import org.apache.log4j.Logger;
import org.jing.core.db.SessionFactory;
import org.jing.web.db.bm.user.api.User;
import org.jing.web.db.bm.user.dto.UserDto;

import java.lang.Exception;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-28 <br>
 */
public class UserDemo {
    static Logger logger = Logger.getLogger(UserDemo.class);

    private UserDemo() throws Exception {
        UserDto user = User.api().qryUserById("34");
        System.out.println(user.getName());
        SessionFactory.getInstance().closeSession();
    }

    public static void main(String[] args) throws Exception {
        new UserDemo();
    }
}
