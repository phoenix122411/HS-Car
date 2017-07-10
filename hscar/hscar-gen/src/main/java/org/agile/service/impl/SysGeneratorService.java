package org.agile.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.agile.dao.SysGeneratorDao;
import org.agile.service.api.ISysGeneratorService;
import org.agile.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysGeneratorService")
public class SysGeneratorService implements ISysGeneratorService {
	@Autowired
	private SysGeneratorDao sysGeneratorDao;

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return sysGeneratorDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysGeneratorDao.queryTotal(map);
	}

	@Override
	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorDao.queryTable(tableName);
	}

	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorDao.queryColumns(tableName);
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip); // 默认模板
//			AgileGenUtils.generatorCode(table, columns, zip); // agile-develop-platform模板
//			PayGenUtils.generatorCode(table, columns, zip); // pay-route模板
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
