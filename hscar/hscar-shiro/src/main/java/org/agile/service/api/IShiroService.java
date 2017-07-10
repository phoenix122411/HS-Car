package org.agile.service.api;

import java.util.Set;

import org.agile.entity.SysUserEntity;
import org.agile.entity.SysUserTokenEntity;

/**
 * shiro相关接口
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface IShiroService {
	/**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
