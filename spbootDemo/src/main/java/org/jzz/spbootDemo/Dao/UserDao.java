package org.jzz.spbootDemo.Dao;

import java.util.List;

import org.jzz.spbootDemo.model.User;

@Deprecated
public interface UserDao {
	List<User> findByName(String name);
}
