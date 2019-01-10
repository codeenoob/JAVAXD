package org.jing.core.lang;

public class ExceptionHandler {
    public static void publish(boolean judge, String errorCode, String msg) throws JingException {
        if (judge) {
            throw new JingException(errorCode, msg);
        }
    }
    
    public static void publish(String errorCode, String msg) throws JingException {
        throw new JingException(errorCode, msg);
    }

    public static void publish(String errorCode, String msg, Throwable throwable) throws JingException {
        throw  new JingException(errorCode, msg, throwable);
    }
}
