package org.agile.controller;

import org.agile.entity.SysUserEntity;
import org.agile.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
