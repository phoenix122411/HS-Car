package org.agile.dao;

import java.util.List;

import org.agile.entity.SysMenuEntity;

/**
 * 菜单管理
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
