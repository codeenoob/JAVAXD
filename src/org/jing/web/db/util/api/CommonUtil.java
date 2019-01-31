package org.jing.web.db.util.api;

import org.jing.core.db.MapperFactory;
import org.jing.core.lang.JingException;
import org.jing.web.db.util.mapper.CommonMapper;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class CommonUtil {

    @Deprecated
    public static int qrySeqCurrentValueByName(String name) throws JingException {
        return MapperFactory.getMapper(CommonMapper.class).qrySeqCurrentValueByName(name);
    }

    public static int qrySeqNextValueByName(String name) throws JingException {
        return MapperFactory.getMapper(CommonMapper.class).qrySeqNextValueByName(name);
    }

    /**
     * Description: Query DataBase Time By Format. <br>
     * Example: CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME) <br>
     *
     * @author: bks <br>
     * @param format <br>
     * @return java.lang.String <br>
     * @throws  <br>
     */
    public static String qryDBDate(String format) throws JingException {
        return MapperFactory.getMapper(CommonMapper.class).qryDBDate(format);
    }
}
