package org.agile.controller;

import java.util.List;
import java.util.Map;

import org.agile.common.ResultVo;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.entity.ScheduleJobLogEntity;
import org.agile.service.api.IScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务日志
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private IScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScheduleJobLogEntity> jobList = scheduleJobLogService.queryList(query);
		int total = scheduleJobLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public ResultVo info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
		
		return ResultVo.ok().put("log", log);
	}
}
