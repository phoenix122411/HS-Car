package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.SysRoleEntity;

/**
 * 角色
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
