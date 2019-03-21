package test.demo2;

import org.jing.core.lang.JingException;
import org.jing.core.lang.annotation.ServiceCode;
import org.jing.core.lang.itf.JService;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-21 <br>
 */
@ServiceCode({"HELLO_WORLD", "HELLO_FUCKING"})
public class TestService implements JService {

    @Override
    public Object execute(Object param) throws JingException {
        System.out.println("Hello World!");
        return null;
    }
}
