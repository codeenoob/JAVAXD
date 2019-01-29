package test.demo1;

import org.jing.core.util.DateUtil;

import java.lang.Exception;
import java.util.Date;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-21 <br>
 */
public class Demo2 {
    private Demo2() throws Exception {
        String dateStr = "2019-01-01 18:19:20";
        Date date = DateUtil.getDate(dateStr, DateUtil.JAVA_DATE_TIME);
        java.sql.Date sqlDate = DateUtil.getSqlDate(dateStr, DateUtil.JAVA_DATE_TIME);
        System.out.println(date.getTime());
        System.out.println(sqlDate.getTime());
        System.out.println(DateUtil.getSqlDateString(sqlDate, DateUtil.JAVA_DATE_TIME));
    }

    public static void main(String[] args) throws Exception {
        new Demo2();
    }
}
