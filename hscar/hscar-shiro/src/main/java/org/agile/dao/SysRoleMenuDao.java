package org.agile.dao;

import java.util.List;

import org.agile.entity.SysRoleMenuEntity;

/**
 * 角色与菜单对应关系
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
