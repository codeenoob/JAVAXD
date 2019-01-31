package org.jing.web.db.util.demo;

import init.Initialization;
import org.jing.core.db.SessionFactory;
import org.jing.core.util.DateUtil;
import org.jing.web.db.util.api.CommonUtil;

import java.lang.Exception;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-29 <br>
 */
public class DateDemo {
    private DateDemo() throws Exception {
        Initialization.getInstance();
        // System.out.println(CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME));
        System.out.println(CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME));
        System.out.println(SessionFactory.getInstance().getSession().getConnection().hashCode());
    }

    public static void main(String[] args) throws Exception {
        new DateDemo();
    }
}
