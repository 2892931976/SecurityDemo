package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface UrlRoleDao {

	/**
	 * 通过链接角色中间表获取角色列表
	 * @param id
	 * @return
	 */
	@Select("SELECT r.`name`\r\n" + 
			"FROM url u \r\n" + 
			"LEFT JOIN url_role ur \r\n" + 
			"ON u.id = ur.`url_id` \r\n" + 
			"LEFT JOIN role r \r\n" + 
			"ON ur.`role_id` = r.`id` \r\n" + 
			"WHERE u.`id`=#{id}")
	List<String> findRolesByUrlId(Integer id);

}
