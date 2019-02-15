package org.jing.core.lang;

import org.apache.log4j.Logger;
import org.jing.core.util.StringUtil;

@SuppressWarnings("unused")
public class ExceptionHandler {
    private static Logger logger = Logger.getLogger(ExceptionHandler.class);

    public static void publish(boolean judge, String errorCode, String msg) throws JingException {
        if (judge) {
            JingException e = new JingException(errorCode, msg);
            logger.error(StringUtil.getErrorInf(e));
            throw e;
        }
    }
    
    public static void publish(String errorCode, String msg) throws JingException {
        JingException e = new JingException(errorCode, msg);
        logger.error(StringUtil.getErrorInf(e));
        throw e;
    }

    public static void publish(String errorCode, String msg, Throwable throwable) throws JingException {
        JingException e = new JingException(errorCode, msg, throwable);
        logger.error(StringUtil.getErrorInf(e));
        throw  e;
    }
}
