package org.agile.controller;

import java.util.List;
import java.util.Map;

import org.agile.annotation.SysLog;
import org.agile.common.ResultVo;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.validator.ValidatorUtils;
import org.agile.entity.ScheduleJobEntity;
import org.agile.service.api.IScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private IScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScheduleJobEntity> jobList = scheduleJobService.queryList(query);
		int total = scheduleJobService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public ResultVo info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
		
		return ResultVo.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public ResultVo save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.save(scheduleJob);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public ResultVo update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public ResultVo delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return ResultVo.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public ResultVo run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return ResultVo.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public ResultVo pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return ResultVo.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public ResultVo resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return ResultVo.ok();
	}

}
