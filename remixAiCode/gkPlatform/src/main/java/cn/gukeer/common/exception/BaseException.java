package cn.gukeer.common.exception;

public class BaseException extends RuntimeException {

	public BaseException() {
		super();
	}
	
	public BaseException(String msg) {
		super(msg);
	}
	
	public BaseException(String msg, Throwable t) {
		super(msg, t);
	}
}
