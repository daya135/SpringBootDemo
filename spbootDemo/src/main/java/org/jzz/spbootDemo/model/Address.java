package org.jzz.spbootDemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "address") 
public class Address implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "address_type", nullable = false)
	private String addressType;	//必须有对应的setter方法才能序列化

	@Column(name = "address", nullable = false)
	private String address;
	
	//如果对address设置了CascadeType.REMOVE，则删除address会波及到user(不管是删除实体还是通过id都会将user删除)，反之又传递到了address的其他行，很可怕！
	//可选属性optional=false,表示user不能为空。
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="userid", referencedColumnName="id", nullable = false)
	private User user;

	public Address(){}
	
	public Address(String addressType, String address){
		this.addressType = addressType;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public String getAddressType() {
		return addressType;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	//重写判等方法，但无法决定插入操作还是更新，即只要id没给，就是插入，fuck！框架只看主键!!
	//测试结果，给了id也是插入操作，服了。。。
//	public boolean equals(Object key) {
//		if(this == key) return true;
//		if(key == null) return false;
//		if(getClass() != key.getClass()) return false;
//		Address aKey = (Address)key;
//		return (this.user.getId() == aKey.getUser().getId()) && (this.addressType == aKey.getAddressType());	//没有判空，不严谨
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (this.user.getId() <= 0 ? 0 : this.user.getId());
//		result = prime * result + (addressType == null ? 0 : addressType.hashCode());
//		return result;
//	}
}
