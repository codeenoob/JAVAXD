package org.jing.web.db.bm.demo;

import init.Initialization;
import org.apache.log4j.Logger;
import org.jing.core.db.SessionFactory;
import org.jing.core.util.DateUtil;
import org.jing.web.db.bm.user.api.User;
import org.jing.web.db.bm.user.dto.ModifyUserDto;
import org.jing.web.db.bm.user.dto.UserDto;

import java.lang.Exception;
import java.util.Date;

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
        user = User.api().qryUserByAccount("admins");
        System.out.println(user.getName());

        System.out.println(User.api().updateLoginDateById(36));
        SessionFactory.getInstance().closeSession();
    }

    public static void main(String[] args) throws Exception {
        new UserDemo();
    }
}
