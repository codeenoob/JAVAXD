package org.jing.core.util;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;

import java.io.*;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-09 <br>
 */
public class FileUtil {
    /**
     * logger. <br>
     */
    private static volatile  Logger logger = null;

    public static Logger getLogger() {
        if (null == logger) {
            logger = Logger.getLogger(FileUtil.class);
        }
        return logger;
    }

    public static String readFile(File file) throws JingException {
        return  readFile(file, true);
    }

    public static String readFile(File file, boolean log) throws JingException {
        String filePath = file.getAbsolutePath();
        if (log) {
            getLogger().info(new StringBuilder("Try To Read File [filePath: ").append(filePath).append("]").toString());
        }
        String retString = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder stbr = new StringBuilder();
            String row = br.readLine();
            if (!GenericUtil.isEmpty(row)) {
                retString = "";
            }
            boolean initFlag = false;
            while (null != row) {
                if (initFlag) {
                    stbr.append("\n");
                }
                stbr.append(row);
                row = br.readLine();
                initFlag = true;
            }
            br.close();
            retString = stbr.toString();
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-FILE-00000",
                new StringBuilder("Failed To Read File [filePath: ").append(filePath).append("]").toString(), e);
        }
        return retString;
    }

    public static String readFile(String filePath, boolean log) throws JingException {
        return readFile(new File(filePath), log);
    }

    public static String readFile(String filePath) throws JingException {
        return readFile(new File(filePath), true);
    }

    /**
     * @Description:  Read File. <br>

     * @author: bks <br>
     * @param filePath <br>
     * @return java.lang.String <br>
     */
    public static String readResource(String filePath, boolean log) throws JingException {
        if (log) {
            getLogger().info(new StringBuilder("Try To Read Resource [filePath: ").append(filePath).append("]").toString());
        }
        StringBuilder stbr = new StringBuilder();
        try {
            Reader reader = Resources.getResourceAsReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (null != line) {
                stbr.append(line).append("\r\n");
                line = br.readLine();
            }
            br.close();
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-FILE-00001",
                new StringBuilder("Failed To Read Resource [filePath: ").append(filePath).append("]").toString(), e);
        }
        return stbr.toString();
    }

    public static String readResource(String filePath) throws JingException {
        return  readResource(filePath, true);
    }
}
