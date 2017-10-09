package cn.struts.test;

import java.math.BigDecimal;
import java.util.Date;

import cn.struts.utils.ActionForm;

public class UserForm extends ActionForm {
	
	private String username;
	private String password;
	private int age;
	private Date utilDate;
	private BigDecimal money;
	
	
	public UserForm(){};
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getUtilDate() {
		return utilDate;
	}
	public void setUtilDate(Date utilDate) {
		this.utilDate = utilDate;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String toString(){
		return "[username"+username+",password"+password+"]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
