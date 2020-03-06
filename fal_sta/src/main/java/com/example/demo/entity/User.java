package com.example.demo.entity;


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
public class User {


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
	 * 社員名
	 */
	@Column(name="empName")
	private String empName;

	/**
	 * 社員番号
	 */
	@Column(name="empNo")
	private Integer empNo;
	
	/**
	 * 社員名
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
	
<<<<<<< HEAD
=======
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
>>>>>>> branch 'master' of https://github.com/shu0603n/falcon.git
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
}