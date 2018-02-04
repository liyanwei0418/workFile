package cn.gukeer.common.entity;

/**
 * Created by conn on 16/3/1.
 */
public class ResultEntity {

    public static final String OK_MSG = "ok";
    public static final int OK_CODE = 0;

    public static final String ERR_MSG = "err";
    public static final int ERR_CODE = -1;

    // http code
    // 非法请求
    public static final int ILLEGAL_CODE = 403;
    // 未登陆
    public static final int NOT_LOGIN_CODE = 401;
    // 不存在
    public static final int NOT_FOUND_CODE = 404;

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultEntity newResultEntity() {
        return newResultEntity(OK_CODE, OK_MSG, null);
    }

    public static ResultEntity newResultEntity(Object obj) {
        return newResultEntity(OK_CODE, OK_MSG, obj);
    }

    public static ResultEntity newResultEntity(String msg, Object obj) {
        return newResultEntity(OK_CODE, msg, obj);
    }

    public static ResultEntity newResultEntity(int code, String msg, Object data) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(code);
        resultEntity.setMsg(msg);
        resultEntity.setData(data);
        return resultEntity;
    }

    public static ResultEntity newErrEntity() {
        return newErrEntity(ERR_CODE, ERR_MSG);
    }

    public static ResultEntity newErrEntity(String msg) {
        return newErrEntity(ERR_CODE, msg);
    }

    public static ResultEntity newErrEntity(int code, String msg) {
        ResultEntity errEntity = new ResultEntity();
        errEntity.setCode(code);
        errEntity.setMsg(msg);
        return errEntity;
    }
}
