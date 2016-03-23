package cn.edu.seu.ytw.exception;

public class RegisterException extends BizException {

	public static final int CONFIRM_ERROR = 2001;
	public static final int USERNAME_EXIST = 2002;
	
	public RegisterException(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

}
