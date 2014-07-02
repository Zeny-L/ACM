package com.manageLogin;

public class Usermes {
	private String idname;
	private String password;
	private String name;
	private String work;
	private String address;
	private String phone;
	private String QQ;
	
	public Usermes(String name, String password, String phone, String address, String work, String QQ) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.work = work;
		this.QQ = QQ;
	}
	public Usermes() {
		super();
	}
	
	public String getIdName() {
		return idname;
	}
	public void setIdName(String idname) {
		this.idname = idname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String QQ) {
		this.QQ = QQ;
	}
	
	public String toString() {
		return "Usermes [name=" + name + ", password=" + password + "]";
	}
}