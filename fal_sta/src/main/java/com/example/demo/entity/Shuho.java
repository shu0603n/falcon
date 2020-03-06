package com.example.demo.entity;




import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 週報情報 Entity
 */
@Entity
@Table(name="shuho")
public class Shuho {

	/**
    * 週報ID
    */
    @Id
    @Column(name="shuhoId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer shuhoId;

	/**
	 * 対象週
	 */
	@Column(name="taishoWeek ")
	private String taishoWeek ;

	/**
	 * 投稿日
	 */
	@Column(name="postedDay")
	private String postedDay;
	
	/**
	 * 作業実績
	 */
	@Column(name="workRecord")
	private String workRecord;
	
	/**
	 * 問題点
	 */
	@Column(name="problem")
	private String problem;
	
	/**
	 * 連絡事項
	 */
	@Column(name="message")
	private String message;
	
	/**
	 * 次週予定
	 */
	@Column(name="schedule")
	private String schedule;
	
	/**
	 * 所感
	 */
	@Column(name="impression")
	private String impression;
	
	/**
	 * ユーザーID Entity
	 */
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName="userId")
    private User user;
	
	
	
	public Integer getShuhoId() {
		return shuhoId;
	}

	public void setShuhoId(Integer shuhoId) {
		this.shuhoId = shuhoId;
	}
	

	public String getTaishoWeek () {
		return taishoWeek ;
	}

	public void setTaishoWeek (String taishoWeek ) {
		this.taishoWeek  = taishoWeek ;
	}
	
	public String getPostedDay() {
		return postedDay;
	}

	public void setPostedDay(String postedDay) {
		this.postedDay = postedDay;
	}
	
	public String getWorkRecord() {
		return workRecord;
	}

	public void setWorkRecord(String workRecord) {
		this.workRecord = workRecord;
	}
	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}