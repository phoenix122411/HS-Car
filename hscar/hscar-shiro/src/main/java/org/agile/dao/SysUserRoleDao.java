package org.agile.dao;

import java.util.List;

import org.agile.entity.SysUserRoleEntity;

/**
 * 用户与角色对应关系
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
