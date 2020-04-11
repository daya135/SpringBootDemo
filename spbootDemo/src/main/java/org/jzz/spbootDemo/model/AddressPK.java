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
@Deprecated	//fuck联合主键，其中一列同时又是外键，带来一堆事！manyToOne配置全tm乱套！弃之不用
@Embeddable
public class AddressPK implements Serializable{
	private static final long serialVersionUID = 1918446199175160467L;
	
	@JoinColumn(name="userid", referencedColumnName="id", nullable = false)
	private int userid;

	@Column(name = "address_type", nullable = false)
	private String addressType;
	
	public AddressPK() {}
	
	public AddressPK(String type) {
		this.addressType = type;
	}
	
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
