package com.springmvc.form.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
	Integer id;
	String name;
	String email;
	String loginId;
	String password;
	String confirmPassword;
	String sex;

	public boolean isNew() {
		return (this.id == null);
	}

	public Integer getId() {
		return id;
	}
	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	@XmlElement
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSex() {
		return sex;
	}
	@XmlElement
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLoginId() {
		return loginId;
	}
	@XmlElement
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", loginId=" + loginId + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + isNew();
	}

}
