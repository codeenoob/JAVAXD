package org.jing.web.db.util.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jing.web.db.util.domain.SeqDMO;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public interface SeqMapper {
    @SelectProvider(type = SeqDMO.class, method = "qryCurrentValueByName")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    @Deprecated
    int qryCurrentValueByName(String name);

    @SelectProvider(type = SeqDMO.class, method = "qryNextValueByName")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    int qryNextValueByName(String name);
}
