package org.jing.web.db.util.demo;

import init.Initialization;
import org.jing.core.db.MapperFactory;
import org.jing.web.db.util.api.SeqUtil;
import org.jing.web.db.util.mapper.SeqMapper;

import java.lang.Exception;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class SeqDemo {
    private SeqDemo() throws Exception {
        Initialization.getInstance();
        System.out.println(SeqUtil.qryNextValueByName("S_USER_ID"));
    }

    public static void main(String[] args) throws Exception {
        new SeqDemo();
    }
}
