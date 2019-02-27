package test.demo1;

import java.lang.Exception;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-14 <br>
 */
public class CommonDemo2 {
    private CommonDemo2() throws Exception {
        HashMap<String, String> map = new HashMap();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        map.put("4", "D");
        map.put("5", "E");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
            map.entrySet().remove(entry);
        }
        System.out.println(map.toString());
    }

    public static void main(String[] args) throws Exception {
        new CommonDemo2();
    }
}
