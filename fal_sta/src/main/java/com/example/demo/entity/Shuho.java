package com.example.demo.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ユーザー情報 Entity
 */
@Entity
@Table(name="user")
public class Shuho {


	/**
    * ユーザーID
    */
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userId;

	/**
	 * パスワード
	 */
	@Column(name="password")
	private String password;

	/**
	 * パスワード
	 */
	@Column(name="epmNo")
	private Integer empNo;
	
	/**
	 * パスワード
	 */
	@Column(name="empName")
	private String empName;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
}