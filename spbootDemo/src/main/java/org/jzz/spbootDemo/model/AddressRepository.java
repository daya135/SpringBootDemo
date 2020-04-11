package org.jzz.spbootDemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	/** 以下已经弃用，映射关系修改了 */
//	/** 联合主键中的一列做入参pk.userid */
//	List<Address> findByPkUserid(int userid);
//	/** 联合主键中的一列做入参pk.addressType */
//	List<Address> findByPkAddressType(String type);
//	/** 联合主键中的两列做入参pk.userid, pk.addressType */
//	Address findByPkUseridAndPkAddressType(int userid, String type);
	
	/** 通过address对象的user.id属性进行查询，实质上和上面提到的联合主键内部属性用法是一致的 */
	List<Address> findByUserId(int userid);
	
//	/** 本地化查询也能直接映射为PO对象 */
	@Query(value="select * from address where id > 0 limit 1,1;", nativeQuery=true)
	Address getTopAddress();
	
	/** 
	 * 单独删除地址表信息，而不影响user表，主要默认功能太草了，直接不解释删用户表数据就很难理解
	 * 删除或更新语句必须加上以下两个注解，fuck！ */
	@Modifying
	@Transactional
	@Query(value="delete from Address where id=?1", nativeQuery=true) 
	void removeById(int id);
	
	//根本没办法执行
//	@Query("select * from User u join Address a on u.id = a.pk.user.id where u.id = ?1 and a.addressType = ?2")
//	List<User> getByPk(int id, String addressType);
}
