package org.jzz.spbootDemo.Dao;

import java.util.List;

import org.jzz.spbootDemo.model.UserSpbt;

public interface UserDao {
	List<UserSpbt> findByName(String name);
}
