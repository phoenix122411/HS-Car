package org.agile.dao;

import java.util.List;
import java.util.Map;

import org.agile.entity.SysUserEntity;

/**
 * 系统用户
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
}
