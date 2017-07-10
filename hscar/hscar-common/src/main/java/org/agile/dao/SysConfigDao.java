package org.agile.dao;

import java.util.List;

import org.agile.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(@Param("key") String key);
	
	/**
	 * 根据key的前缀，半模糊查询valueList
	 */
	List<SysConfigEntity> queryListHalfLikeKey(@Param("key") String key);
	
}
