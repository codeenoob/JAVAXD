package org.jing.web.db.bm.user.api;

import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
import org.jing.core.lang.JingException;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.bm.user.mapper.UserMapper;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-28 <br>
 */
public class User {
    private static Logger logger = Logger.getLogger(User.class);

    public static User api() {
        return new User();
    }

    public void saveUser(UserDto user) throws JingException {
        MapperFactory.getMapper(UserMapper.class).saveUser(user);
    }

    public UserDto qryUserById(String id) throws JingException {
        return MapperFactory.getMapper(UserMapper.class).qryUserById(id);
    }
}
