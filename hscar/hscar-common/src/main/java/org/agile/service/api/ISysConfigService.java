package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysConfigService {
	
	/**
	 * 保存配置信息
	 */
	public void save(SysConfigEntity config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfigEntity config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 * 获取List列表
	 */
	public List<SysConfigEntity> queryList(Map<String, Object> map);
	/**
	 * 获取总记录数
	 */
	public int queryTotal(Map<String, Object> map);
	
	/**
	 * 获取配置信息
	 */
	public SysConfigEntity queryObject(Long id);
	
	/**
	 * 根据key，获取配置的value值
	 * @param key           key
	 * @param defaultValue  缺省值
	 */
	public String getValue(String key, String defaultValue);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
	/**
	 * 根据key的前缀，半模糊查询valueList
	 * @param key           key
	 */
	public List<SysConfigEntity> getListHalfLikeKey(String key);
}
