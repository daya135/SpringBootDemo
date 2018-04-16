package org.jzz.spbootDemo.model;

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

@Entity
@Table(name = "user_test") /* 指定表名，默认用类名 */
public class UserSpbt {
	
	@Id	/*标示主键  */
	@GeneratedValue(strategy = GenerationType.AUTO) /* 自增列  */
	private Long id;
	
	@Column(nullable = false, name = "user_name") /* 指定字段名，默认用列名 */
	private String userName;
	
	@Column(nullable = true)
	private int age;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)  /* 定义日期精度 */
	private Date birth;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<AddressSpbt> address;

	public UserSpbt(){}
	
	public UserSpbt(Long id, String name){
		this.id = id;
		this.userName = name;
	}
	
	public UserSpbt(String name) {
		this.userName = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	public Set<AddressSpbt> getAddress() {
		return address;
	}
	public void setAddress(Set<AddressSpbt> address) {
		this.address = address;
	}
	
    @Override
    public String toString() {
        return String.format("user [id=%d, userName=%s, age=%d]", id, userName, age);
    }
}
