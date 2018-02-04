package cn.gukeer.common.exception;

/**
 * Created by conn on 2016/8/5.
 */
public class  ParamException extends CustomException{
    public ParamException() {
        this("参数错误");
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
