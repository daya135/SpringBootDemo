package org.jzz.spbootDemo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/** 地址表复合主键类
 * 必须重写hashcode和equals方法
 * 必须实现序列化接口
 * 提供无参构造函数
 * 类名末尾加上PK
 */
@Embeddable
public class AddressPK implements Serializable{
	private static final long serialVersionUID = 1918446199175160467L;
	
//	@Column	//加了会报错：@Column(s) not allowed on a @ManyToOne property: 因为这个属性是外键，所以应该用JoinColumn代替
	@JoinColumn(name="userid", referencedColumnName="id", nullable = false)		//设置外键
	private int userid;

	@Column(name = "address_type", nullable = false)
	private String addressType;
	
	public AddressPK() {}
	
	public AddressPK(int userid, String type) {
		this.userid = userid;
		this.addressType = type;
	}
	
	public boolean equals(Object key) {
		if(this == key) return true;
		if(key == null) return false;
		if(getClass() != key.getClass()) return false;
		AddressPK aKey = (AddressPK)key;
		return (this.userid == aKey.getUserid()) && (this.addressType == aKey.getAddressType());	//没有判空，不严谨
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (userid <= 0 ? 0 : userid);
		result = prime * result + (addressType == null ? 0 : addressType.hashCode());
		return result;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
}
