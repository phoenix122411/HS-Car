package org.agile.service.api;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysUserRoleService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void delete(Long userId);
}
