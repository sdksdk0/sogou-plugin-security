package cn.sogoucloud.plugin.security.exception;

/**
 * 非法访问时抛出
 * @author lenovo
 *
 */
public class AuthcException extends Exception {
	
	public AuthcException(){
		super();
	}
	
	public AuthcException(String message){
		super(message);
	}
	
	public AuthcException(String message,Throwable cause){
		super(message,cause);
	}
	
	public AuthcException(Throwable cause){
		super(cause);
	}

}
