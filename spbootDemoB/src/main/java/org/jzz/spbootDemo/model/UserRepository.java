package org.jzz.spbootDemo.model;

import java.util.List;

import org.jzz.spbootDemo.model.UserSpbt;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;  

public interface UserRepository extends JpaRepository<UserSpbt, Integer>{
	
	List<UserSpbt> findByUserName(String name);

	List<UserSpbt> findByUserNameContaining(String string, Pageable pageable);
	
	List<UserSpbt> findByUserNameContaining(String string);
	
	/* 注意单词之间的By不要缺了。。 */
	UserSpbt findTopByOrderByAgeDesc();
	
	@Query("select count(*) from UserSpbt where userName = ?1")
	int countByUserName(String name);
	
	/* 多表自定义结果集查询，必须指定字段名，不能用u.*的形式 */
	/* 函数名不能用findxxx的形式，否则会报错！！ */
	/* 同名字段不好使用sort对象时，将排序写在sql语句中 */
	@Query("select u.id, u.userName, ad.id, ad.address from UserSpbt u, AddressSpbt ad where u.id = ad.userid and u.id = ?1 order by ad.id DESC")
	List<Object[]> getUserAndAddress(Long userid, Pageable pageable);
	
}
