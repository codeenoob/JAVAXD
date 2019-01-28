package org.jing.web.db.util.demo;

import init.Initialization;
import org.apache.log4j.Logger;
import org.jing.core.util.DateUtil;
import org.jing.web.db.util.api.CommonUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class SeqDemo {
    static Logger logger = Logger.getLogger(SeqDemo.class);

    private SeqDemo() throws Exception {
        Initialization.getInstance();
        System.out.println(CommonUtil.qrySeqNextValueByName("S_USER_ID"));
        logger.info(CommonUtil.qryDBDate(DateUtil.DB_DATE_TIME));
    }

    public static void main(String[] args) throws Exception {
        new SeqDemo();
    }
}
