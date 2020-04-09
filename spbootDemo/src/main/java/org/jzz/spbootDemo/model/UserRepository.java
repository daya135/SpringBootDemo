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
	
	List<UserSpbt> findByUserNameAndAge(String userName, int age);
	
	/* 注意单词之间的By不要缺了。。 */
	UserSpbt findTopByOrderByAgeDesc();
	
	/* 实体SQL查询 */
	@Query("select count(*) from UserSpbt where userName = ?1")
	int countByUserName(String name);
	
	/* 本地SQL查询 */
	/* 函数名不要用findxxx的形式，否则可能和内置的规则冲突！！ */
	/* 同名字段不好使用sort对象时，将排序写在sql语句中 */
	@Query(value = "select a.id, a.username, a.birthday, c.address from user a , user_address b, address c "
			+ "where a.id = b.user_id and b.address_id = c.id and a.id=:userid", nativeQuery=true)
	List<Object[]> getUserAndAddress(int userid, Pageable pageable);
	
}
