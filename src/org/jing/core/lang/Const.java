package org.jing.core.lang;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class Const {
    public static final String STATE_ACTIVE = "A";

    public static final String STATE_INACTIVE = "X";

    public final static String CARRIER_ROOT_NODE = "jing";

    public final static String SYSTEM_FILE_SEPERATOR = Configuration.getInstance().getLocalProperty("file.separator");

    public final static int SYSTEM_MAX_BYTES_SIZE = 100000000;

    public final static String CFG_TABLELIST_FILENAME = "tableList.xml";
}
