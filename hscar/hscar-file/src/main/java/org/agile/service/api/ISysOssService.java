package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysOssService {
	
	SysOssEntity queryObject(Long id);
	
	List<SysOssEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysOssEntity sysOss);
	
	void update(SysOssEntity sysOss);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
