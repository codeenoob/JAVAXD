package org.jing.core.lang;

public class JingException extends Exception{
    /**
     * Description: serialVersionUID <br>
     */
    private static final long serialVersionUID = 1L;

    public JingException() {
        super();
    }
    
    public JingException(String msg) {
        super(msg);
    }
    
    public JingException(String errorCode, String msg) {
        super(new StringBuilder("[ErrorCode: ").append(errorCode).append("], Error Description: [").append(msg).append("]").toString());
    }

    public JingException(String errorCode, String msg, Throwable throwable) {
        super(new StringBuilder("[ErrorCode: ").append(errorCode).append("], Error Description: [").append(msg).append("]").toString(), throwable);
    }
}
