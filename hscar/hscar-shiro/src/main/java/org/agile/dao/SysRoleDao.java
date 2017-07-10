package org.agile.dao;

import java.util.List;

import org.agile.entity.SysRoleEntity;

/**
 * 角色管理
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
