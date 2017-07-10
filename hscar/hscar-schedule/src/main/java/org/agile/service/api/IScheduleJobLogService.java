package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface IScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLogEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
