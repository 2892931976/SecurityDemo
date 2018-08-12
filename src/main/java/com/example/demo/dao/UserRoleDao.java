package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface UserRoleDao {

	/**
	 * 通过用户id获得角色名列表
	 * @param id
	 * @return
	 */
	@Select("SELECT r.`name`\r\n" + 
			"FROM USER u \r\n" + 
			"LEFT JOIN user_role ur \r\n" + 
			"ON u.`id` = ur.`user_id` \r\n" + 
			"LEFT JOIN role r \r\n" + 
			"ON ur.`role_id` = r.`id` \r\n" + 
			"WHERE u.`id`=#{id}")
	List<String> getRolesByUserId(Integer id);

}
