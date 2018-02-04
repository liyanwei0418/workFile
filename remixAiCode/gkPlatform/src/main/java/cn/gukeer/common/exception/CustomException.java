package cn.gukeer.common.exception;

/**
 * Created by conn on 2016/8/5.
 */
public class CustomException extends RuntimeException{
    private boolean checkErrHandle = true;

    public CustomException(){

    }

    public CustomException(String message) {
        super(message);

    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public boolean isCheckErrHandle() {
        return checkErrHandle;
    }

    public void setCheckErrHandle(boolean checkErrHandle) {
        this.checkErrHandle = checkErrHandle;
    }
}
