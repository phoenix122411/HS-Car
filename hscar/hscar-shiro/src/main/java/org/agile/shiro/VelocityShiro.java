package org.agile.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro权限标签(Velocity版)
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class VelocityShiro {

	/**
	 * 是否拥有该权限
	 * @param permission  权限标识
	 * @return   true：是     false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

}
