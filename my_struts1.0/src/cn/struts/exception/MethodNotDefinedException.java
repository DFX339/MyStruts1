package cn.struts.exception;
/*
 * DispatchAction�ķ��������ں�û��ָ�����������쳣
 * */
public class MethodNotDefinedException extends RuntimeException {

	public MethodNotDefinedException(){}
	
	public MethodNotDefinedException(String msg){
		super(msg);
	}
}
