package org.jzz.spbootDemo.model;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findById(int id);
	
	List<User> findByUserName(String name);

	List<User> findByUserNameContaining(String string, Pageable pageable);
	
	List<User> findByUserNameContaining(String string);
	
	List<User> findByUserNameAndAge(String userName, int age);
	
	@Query(value="select * from user where id > 0 limit 1,1;", nativeQuery=true)
	User getTopUser();
	
	/* 注意单词之间的By不要缺了。。 */
	User findTopByOrderByAgeDesc();
	
	/* 实体SQL查询 */
	@Query("select count(*) from User where userName=:name")
	int countByUserName(String name);
	
	/* 本地SQL查询 */
	/* 函数名不要用findxxx的形式，否则可能和内置的规则冲突！！ */
	/* 同名字段不好使用sort对象时，将排序写在sql语句中 */
	@Query(value = "select a.id, a.username, a.age, date_format(a.birthday, '%Y-%m-%d %H:%i:%S'),b.address_type, b.address from user a left join address b "
			+ "on a.id = b.userid where a.id=:userid", nativeQuery=true)
	List<Object[]> getUserAndAddress(int userid);
	
}
