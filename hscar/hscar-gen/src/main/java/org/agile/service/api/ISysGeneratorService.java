package org.agile.service.api;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysGeneratorService {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/**
	 * 生成代码
	 */
	byte[] generatorCode(String[] tableNames);
}
