package org.jing.core.db;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.jing.core.lang.JingException;
import org.jing.core.util.DateUtil;
import org.jing.core.util.StringUtil;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-29 <br>
 */
@MappedJdbcTypes(JdbcType.DATE)
@MappedTypes(String.class)
public class SqlDateTime2JavaStringTypeHandler implements TypeHandler<String> {
    @Override public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType)
        throws SQLException {
        try {
            preparedStatement.setDate(i, StringUtil.isNotEmpty(s) ? DateUtil.getSqlDate(s, DateUtil.JAVA_DATE_TIME) : null);
        }
        catch (JingException e) {
            throw new SQLException(e);
        }
    }

    @Override public String getResult(ResultSet resultSet, String s) throws SQLException {
        Date date = resultSet.getDate(s);
        return DateUtil.getSqlDateString(date, DateUtil.JAVA_DATE_TIME);
    }

    @Override public String getResult(ResultSet resultSet, int i) throws SQLException {
        Date date = resultSet.getDate(i);
        return DateUtil.getSqlDateString(date, DateUtil.JAVA_DATE_TIME);
    }

    @Override public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        Date date = callableStatement.getDate(i);
        return DateUtil.getSqlDateString(date, DateUtil.JAVA_DATE_TIME);
    }
}
