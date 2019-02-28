package org.jing.web.db.bm.user.api;

import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
import org.jing.core.lang.JingException;
import org.jing.core.util.DateUtil;
import org.jing.web.db.bm.user.dto.ModifyUserDto;
import org.jing.web.db.bm.user.dto.UserDto;
import org.jing.web.db.bm.user.mapper.UserMapper;
import org.jing.web.db.util.api.CommonUtil;

import java.util.Date;

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

    public UserDto qryUserByAccount(String account) throws JingException {
        return MapperFactory.getMapper(UserMapper.class).qryUserByAccount(account);
    }

    public int modifyUserByAccountOrId(ModifyUserDto userDto) throws JingException {
        return MapperFactory.getMapper(UserMapper.class).modifyUserByAccountOrId(userDto);
    }

    public int updateLoginDateById(int id, String date) throws JingException {
        ModifyUserDto modifyUser = new ModifyUserDto();
        modifyUser.setId(id);
        modifyUser.setLastLoginDate(date);
        return modifyUserByAccountOrId(modifyUser);
    }

    /**
     * Description:  <br>
     * Update the last login date by database timestamp. <br>
     *
     * @author: bks <br>
     * @param id <br>
     * @return int <br>
     * @throws JingException <br>
     */
    public int updateLoginDateById(int id) throws JingException {
        String date = CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME);
        ModifyUserDto modifyUser = new ModifyUserDto();
        modifyUser.setId(id);
        modifyUser.setLastLoginDate(date);
        return modifyUserByAccountOrId(modifyUser);
    }

    public void loginById() {

    }
}
