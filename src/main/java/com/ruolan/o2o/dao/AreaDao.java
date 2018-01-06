package com.ruolan.o2o.dao;

import java.util.List;

import com.ruolan.o2o.entity.Area;


public interface AreaDao {

	/**
	 * 查询地域列表
	 * 
	 * @return 集合
	 */
	List<Area> queryArea();

}
