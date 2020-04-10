package org.jzz.spbootDemo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "address") 
public class Address {
	private static final long serialVersionUID = 1918446199175160467L;
	
	@EmbeddedId
	private AddressPK pk;
	
	@Column(name = "address", nullable = false)
	private String address;
	
//xxx	@JoinColumn(name="userid", referencedColumnName="id", nullable = false)		//去掉userid属性，将此注解放到这个位置也能工作，问题就是很难用，即address对象没有userid属性用
	//ManyToOne到底有什么用？？对于查询来说，除了往数据库新增了一个user_id字段，就没见着什么变化！
//	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)	//添加@ManyToOne可以避免生成中间表
//	@JsonBackReference	//序列化时排除此属性，否则就死循环了！！
//	private User user;
	
	public Address(){}
	
	public Address(AddressPK key, String address){
		this.pk = key;
		this.address = address;
	}

	public AddressPK getId() {
		return pk;
	}

	public void setId(AddressPK id) {
		this.pk = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
