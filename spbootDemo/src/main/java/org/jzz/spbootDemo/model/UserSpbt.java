package org.jzz.spbootDemo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.annotation.JsonFormat;

import javassist.Loader.Simple;

@Entity
@Table(name = "user") /* 指定表名，默认用类名 */
public class UserSpbt {
	
	@Id	/*标示主键  */
	@GeneratedValue(strategy = GenerationType.AUTO) /* 自增列  */
	private int id;
	
	@Column(nullable = false, name = "username") /* 指定字段名，默认用列名 */
	private String userName;
	
	@Column(nullable = true)
	private int age;
	
	@Column(nullable = true, name = "birthday")
	@Temporal(TemporalType.DATE)  /* 定义日期精度 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	//入参，仅针对@ModelAttribute，应该是spring的东西
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	//出参，springboot中，已经用配置文件中配置了
	private Date birth;

	public UserSpbt(){}
	
	public UserSpbt(int id, String name){
		this.id = id;
		this.userName = name;
	}
	
	public UserSpbt(String name) {
		this.userName = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	//重写这个方法会导致requestBody反序列化失败，时间格式错误，原因未知
//    @Override
//    public String toString() {
//        return String.format("user [id=%d, userName=%s, age=%d, birth=%s]", id, userName, age, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(birth));
//    }
}
