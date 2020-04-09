package org.jzz.spbootDemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "address") 
public class AddressSpbt {
	
	@Id	/*标示主键  */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "userid")
	private Long userId;
	
	@Column(nullable = false)
	private String address;
	
	public AddressSpbt(){}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
    @Override
    public String toString() {
        return String.format("address [id=%s, address=%s]", userId, address);
    }
}
