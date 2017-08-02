package com.hai.chao.sun.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private String id;
	private String userName;
	private String name;
	private Integer age;
	private String sex;
	private Date birthday;
	private Date created;
	private Date updated;
	@JsonIgnore//转换成json时，忽略该属性内容   
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	
	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", birthday=" + birthday + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	
}
