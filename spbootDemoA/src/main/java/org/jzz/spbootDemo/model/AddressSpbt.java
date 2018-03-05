package org.jzz.spbootDemo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.catalina.User;


@Entity
@Table(name = "address_test") 
public class AddressSpbt {
	
	@Id	/*标示主键  */
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Long userid;
	
	@Column(nullable = false)
	private String address;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="userid", referencedColumnName="id", insertable=false, updatable=false)
	private UserSpbt user;
	
	public AddressSpbt(){}

	public AddressSpbt(Long userid, String address){
		this.userid = userid;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
    @Override
    public String toString() {
        return String.format("address [id=%s, userid=%s, address=%s]", id, userid, address);
    }
}
