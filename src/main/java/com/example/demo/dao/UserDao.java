package com.example.demo.dao;

import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

public interface UserDao {
	/**
	 * 通过用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	@Select("select * from user where username=#{0} and password=#{1}")
	User findUserByUserNameAndPassword(String username, String password);

}
