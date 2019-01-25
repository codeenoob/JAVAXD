package org.jing.web.db.util.api;

import org.jing.core.db.MapperFactory;
import org.jing.core.lang.JingException;
import org.jing.web.db.util.mapper.SeqMapper;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class SeqUtil {
    @Deprecated
    public static int qryCurrentValueByName(String name) throws JingException {
        return MapperFactory.getMapper(SeqMapper.class).qryCurrentValueByName(name);
    }

    public static int qryNextValueByName(String name) throws JingException {
        return MapperFactory.getMapper(SeqMapper.class).qryNextValueByName(name);
    }
}
