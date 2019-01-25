package test.demo1;

import org.jing.core.util.FileUtil;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-21 <br>
 */
public class CommonDemo1 {
    private CommonDemo1() throws Exception {
        String resource = "org/jing/cfg/mybatis-config.xml";
        System.out.println(FileUtil.readResource(resource, false));
        /*System.out.println(new File(resource).exists());
        ClassLoader classLoader[] = new ClassLoader[] { null, null, Thread.currentThread().getContextClassLoader(), this.getClass().getClassLoader(), ClassLoader.getSystemClassLoader()};
        int size = classLoader.length;
        for (int i = 0; i < size; i++) {
            ClassLoader cl = classLoader[i];
            InputStream returnValue = null;
            if (null != cl) {
                returnValue = cl.getResourceAsStream(resource);
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }
            }
            System.out.println(i + "==" + returnValue);
            byte b[] = new byte[10000];
            if (null != returnValue) {
                returnValue.read(b);
                System.out.println(new String(b));
            }
        }*/
    }
    public static void main(String[] args) throws Exception {
        new CommonDemo1();
    }
}
