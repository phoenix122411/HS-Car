package org.agile.controller;

import org.agile.common.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限判断
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-10
 */
@Controller
@RequestMapping("/sys/shiro")
public class SysShiroController {
	
	/**
	 * 判断是否有permission权限
	 */
	@ResponseBody
	@RequestMapping("/hasPermission")
	public ResultVo hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		boolean hasPermission = (subject!=null && subject.isPermitted(permission));
		return ResultVo.ok().put("hasPermission", hasPermission);
	}
}
