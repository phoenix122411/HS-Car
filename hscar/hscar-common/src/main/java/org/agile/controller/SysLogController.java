package org.agile.controller;

import java.util.List;
import java.util.Map;

import org.agile.common.ResultVo;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.entity.SysLogEntity;
import org.agile.service.api.ISysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统日志
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private ISysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysLogEntity> sysLogList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
}
