package org.jing.core.util;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.jing.core.lang.Const;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;

import java.io.*;
import java.util.Properties;

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

    private static ClassLoader defaultClassLoader;

    private static ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    public static boolean log(boolean flag) {
        if (flag) {
            if (null == logger) {
                logger = Logger.getLogger(FileUtil.class);
            }
            return  true;
        }
        else {
            return  false;
        }
    }

    public static String readFile(File file) throws JingException {
        return  readFile(file, true);
    }

    public static String readFile(File file, boolean log) throws JingException {
        String filePath = file.getAbsolutePath();
        if (log(log)) {
            logger.info(new StringBuilder("Try To Read File [filePath: ").append(filePath).append("]").toString());
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
            ExceptionHandler.publish("UTIL-FILE-00003",
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
     * Description:  Read File. <br>

     * @author: bks <br>
     * @param filePath <br>
     * @return java.lang.String <br>
     */
    public static String readProperties2String(String filePath, boolean log) throws JingException {
        if (log(log)) {
            logger.info(new StringBuilder("Try To Read File [filePath: ").append(filePath).append("]").toString());
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

    public static String readProperties2String(String filePath) throws JingException {
        return  readProperties2String(filePath, true);
    }

    public static Properties readProperties(String filePath, boolean log) throws JingException {
        if (log(log)) {
            logger.info(new StringBuilder("Try To Read File [filePath: ").append(filePath).append("]").toString());
        }
        try {
            return Resources.getResourceAsProperties(filePath);
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-FILE-00002",
                new StringBuilder("Failed To Read Properties [filePath: ").append(filePath).append("]").toString(), e);
        }
        return null;
    }

    public static Properties readProperties(String filePath) throws JingException {
        return readProperties(filePath, true);
    }

    public static String readResource(String filePath, boolean log) throws JingException {
        if (log(log)) {
            logger.info(new StringBuilder("Try To Read File [filePath: ").append(filePath).append("]").toString());
        }
        try {
            ClassLoader classLoader[] = new ClassLoader[] {
                null,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                FileUtil.class.getClassLoader(),
                systemClassLoader
            };
            int size = classLoader.length;
            for (int i$ = 0; i$ < size; i$++) {
                ClassLoader classLoader$ = classLoader[i$];
                InputStream is = null;
                if (null != classLoader$) {
                    is = classLoader$.getResourceAsStream(filePath);
                    if (null == is) {
                        is = classLoader$.getResourceAsStream(
                            new StringBuilder(Const.SYSTEM_FILE_SEPERATOR).append(filePath).toString());
                    }
                    if (null != is) {
                        StringBuilder stbr$ = new StringBuilder();
                        int maxLength$ = is.available();
                        int size$ = maxLength$ / Const.SYSTEM_MAX_BYTES_SIZE + 1;
                        for (int j$ = 0; j$ < size$; j$++) {
                            int off$ = j$ * Const.SYSTEM_MAX_BYTES_SIZE;
                            int len$ = (j$ + 1) * Const.SYSTEM_MAX_BYTES_SIZE > maxLength$
                                ? maxLength$
                                : Const.SYSTEM_MAX_BYTES_SIZE;
                            byte[] bytes$ = new byte[len$];
                            is.read(bytes$, off$, len$);
                            stbr$.append(new String(bytes$));
                        }
                        return stbr$.toString();
                    }
                }
            }
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-FILE-00004",
                new StringBuilder("Failed To Read Properties [filePath: ").append(filePath).append("]").toString(), e);
        }
        return null;
    }
}
