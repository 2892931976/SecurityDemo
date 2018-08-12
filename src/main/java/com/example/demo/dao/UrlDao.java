package com.example.demo.dao;

import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Url;

public interface UrlDao {
	/**
	 * 通过url链接名获取url对象
	 * @param urlString
	 * @return
	 */
	@Select("select * from url where name=#{urlName}")
	Url findUrlByName(String urlName);

}
