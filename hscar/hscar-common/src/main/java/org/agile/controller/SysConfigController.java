package org.agile.controller;

import java.util.List;
import java.util.Map;

import org.agile.annotation.SysLog;
import org.agile.common.ResultVo;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.validator.ValidatorUtils;
import org.agile.entity.SysConfigEntity;
import org.agile.service.api.ISysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {
	@Autowired
	private ISysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysConfigEntity> configList = sysConfigService.queryList(query);
		int total = sysConfigService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public ResultVo info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.queryObject(id);
		
		return ResultVo.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public ResultVo save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public ResultVo update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public ResultVo delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return ResultVo.ok();
	}

	/**
	 * 根据key的前缀，半模糊查询valueList
	 */
	@RequestMapping("/get_list_half_like_key")
	public ResultVo get_list_half_like_key(String key){
		List<SysConfigEntity> configList = sysConfigService.getListHalfLikeKey(key);
		
		return ResultVo.ok().put("configList", configList);
	}
}
