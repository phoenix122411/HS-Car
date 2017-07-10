package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogEntity sysLog);
	
	void update(SysLogEntity sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
