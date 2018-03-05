package org.jzz.spbootDemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;  

public interface AddressRepository extends JpaRepository<AddressSpbt, Integer>{
	
	
	/* 注意此处写sql时是关联对象的属性，而不是数据库的字段名，完全按照操作对象的方式操作数据库！！ */
	@Query("select address from AddressSpbt address join address.user b where address.userid = b.id and b.userName = :name")
	List<AddressSpbt> findByAddressByUserName(@Param("name") String name);
}
