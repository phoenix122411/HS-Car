package org.agile.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] ids);

	T queryObject(Object object);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object object);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
