package org.jzz.spbootDemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, AddressPK>{
	
	/** 联合主键中的一列做入参pk.userid */
	List<Address> findByPkUserid(int userid);
	/** 联合主键中的一列做入参pk.addressType */
	List<Address> findByPkAddressType(String type);
	/** 联合主键中的两列做入参pk.userid, pk.addressType */
	Address findByPkUseridAndPkAddressType(int userid, String type);
	
	//根本没办法执行
//	@Query("select * from User u join Address a on u.id = a.pk.user.id where u.id = ?1 and a.addressType = ?2")
//	List<User> getByPk(int id, String addressType);
}
