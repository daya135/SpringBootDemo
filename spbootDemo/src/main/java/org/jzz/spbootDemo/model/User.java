package org.jzz.spbootDemo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user") /* 指定表名，默认用类名 */
public class User {
	
	@Id	/*标示主键  */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* 自增列，GenerationType.auto会有重复的问题,应该交由数据库管理  */
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(nullable = false, name = "username") /* 指定字段名，默认用列名 */
	private String userName;
	
	@Column(nullable = true)
	private int age;
	
	
//	@Temporal(TemporalType.DATE)  /* 定义日期精度 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	//springboot中，已经用配置文件中配置了（requestBody和responseBody都适用）
	@Column(nullable = true, name = "birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	//反序列化入参格式，仅针对@ModelAttribute
	private Date birth;
	
//	@JsonBackReference
	//一对多属性，若将关系维护方在多方，需设置mappedBy
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")	//当这里改为懒加载时，序列化会报错！
	private List<Address> addresss;

	public User(){}
	public User(String userName, int age, Date birth){
		this.userName = userName;
		this.age = age;
		this.birth = birth;
	}
	
	public User(int id, String name){
		this.id = id;
		this.userName = name;
	}
	
	public User(String name) {
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

	public List<Address> getAddresss() {
		return addresss;
	}

	public void setAddresss(List<Address> addresss) {
		this.addresss = addresss;
	}
	
	//重写这个方法会导致requestBody反序列化失败，时间格式错误，原因未知
//    @Override
//    public String toString() {
//        return String.format("user [id=%d, userName=%s, age=%d, birth=%s]", id, userName, age, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(birth));
//    }
}
