package org.jing.core.lang;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class Coniguration {
    private static Coniguration ourInstance = new Coniguration();

    public static Coniguration getInstance() {
        return ourInstance;
    }

    private Coniguration() {
    }
}
