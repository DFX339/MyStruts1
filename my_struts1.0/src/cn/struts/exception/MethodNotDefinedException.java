package cn.struts.exception;
/*
 * DispatchAction的方法不存在和没有指定方法名的异常
 * */
public class MethodNotDefinedException extends RuntimeException {

	public MethodNotDefinedException(){}
	
	public MethodNotDefinedException(String msg){
		super(msg);
	}
}
