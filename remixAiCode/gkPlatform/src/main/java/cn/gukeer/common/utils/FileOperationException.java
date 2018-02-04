package cn.gukeer.common.utils;

/**
 * 文件操作异常类
 * 
 * @author CC
 *
 */
public class FileOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileOperationException() {
	}

	public FileOperationException(String errorMessage) {
		super(errorMessage);
	}

	public FileOperationException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

}
