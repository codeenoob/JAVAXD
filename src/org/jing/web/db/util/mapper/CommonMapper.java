package org.jing.web.db.util.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public interface CommonMapper {
    // @SelectProvider(type = CommonDMO.class, method = "qrySeqCurrentValueByName")
    @Select("SELECT ${seqName}.CURRVAL FROM DUAL")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    @Deprecated
    int qrySeqCurrentValueByName(@Param("seqName") String seqName);

    // @SelectProvider(type = CommonDMO.class, method = "qrySeqNextValueByName")
    @Select("SELECT ${seqName}.NEXTVAL FROM DUAL")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    int qrySeqNextValueByName(@Param("seqName") String seqName);

    @Select("SELECT TO_CHAR(SYSDATE, #{format}) FROM DUAL")
    String qryDBDate(@Param("format") String format);
}
