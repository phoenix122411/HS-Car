package org.agile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agile.dao.SysUserRoleDao;
import org.agile.service.api.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户与角色对应关系
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Service("sysUserRoleService")
public class SysUserRoleService implements ISysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.delete(userId);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(Long userId) {
		sysUserRoleDao.delete(userId);
	}
}
