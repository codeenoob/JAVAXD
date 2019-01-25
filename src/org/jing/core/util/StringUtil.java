package org.jing.core.util;

import org.apache.log4j.Logger;
import org.jing.core.lang.JingException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-17 <br>
 */
public class StringUtil {
    private static Logger logger = Logger.getLogger(StringUtil.class);

    public final static int PAD_MODEL_LEFT = 0;

    public final static int PAD_MODEL_RIGHT = 1;

    /**
     * Description: Get A Default String If Parameter String Is Empty. <br>
     *
     * @author bks <br>
     * @param string <br>
     * @param defaultString <br>
     * @return <br>
     */
    public static String ifEmpty(String string, String defaultString) {
        return isEmpty(string) ? defaultString : string;
    }

    /**
     * Description: Get An Empty String If Parameter String Is Empty. <br>
     *
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */
    public static String ifEmpty(String string) {
        return isEmpty(string) ? "" : string;
    }

    /**
     * Description: Get Default String If Parameter String Is Not Empty. <br>
     *
     * @author bks <br>
     * @param string <br>
     * @param defaultString <br>
     * @return <br>
     */
    public static String ifNotEmpty(String string, String defaultString) {
        if (null != string && string.length() != 0) {
            return defaultString;
        }
        else {
            return string;
        }
    }

    /**
     * Description: Judge If String Is Null Or Empty String <br>
     *
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */
    public static boolean isEmpty(String string) {
        return null == string || string.length() == 0;
    }

    /**
     * Description: Judge If String Is Not Null And Empty String <br>
     *
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */
    public static boolean isNotEmpty(String string) {
        return null != string && string.length() != 0;
    }

    /**
     * Description: Repeat String <br>
     *
     * @author bks <br>
     * @param repeatChar <br>
     * @param num <br>
     * @return <br>
     */
    public static String repeat(char repeatChar, int num) {
        StringBuilder stb = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            stb.append(repeatChar);
        }
        return stb.toString();
    }

    /**
     * Description: Repeat String <br>
     *
     * @author bks <br>
     * @param repeatString <br>
     * @param num <br>
     * @return <br>
     */
    public static String repeat(String repeatString, int num) {
        StringBuilder stb = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            stb.append(repeatString);
        }
        return stb.toString();
    }

    /**
     * Description: Get Error Information <br>
     *
     * @author bks <br>
     * @param exception <br>
     * @return <br>
     * @throws JingException <br>
     */
    public static String getErrorInf(Throwable exception) throws JingException {
        if (Objects.isNull(exception)) return "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exception.printStackTrace(new PrintStream(baos));
        try {
            baos.close();
            return baos.toString();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            throw new JingException("Fail To Get Error Information.");
        }
        finally {
            baos = null;
        }
    }

    /**
     * Description: Cut Or Add. <br>
     *
     * @author bks <br>
     * @param string <br>
     * @param model <br>
     * @param c <br>
     * @return <br>
     */
    public static String pad(String string, int model, char c) {
        if (string.length() > 10) {
            if (model == PAD_MODEL_LEFT) {
                return string.substring(string.length() - 11);
            }
            else if (model == PAD_MODEL_RIGHT) {
                return string.substring(0, 10);
            }
            else {
                return string;
            }
        }
        else {
            if (model == PAD_MODEL_LEFT) {
                return new StringBuilder(repeat(c, 10 - string.length())).append(string).toString();
            }
            else if (model == PAD_MODEL_RIGHT) {
                return new StringBuilder(string).append(repeat(c, 10 - string.length())).toString();
            }
            else {
                return string;
            }
        }
    }

    public static String getErrorStack(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        try {
            pw.close();
            sw.close();
        }
        catch (Exception e) {
            logger.error(e);
        }
        return sw.toString();
    }
}
