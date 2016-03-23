package cn.edu.seu.ytw.exception;

public class LoginException extends BizException {

	public static final int NAME_NOT_EXIST = 1001;
	public static final int PASSWORD_ERROR = 1002;

	public LoginException(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

}
