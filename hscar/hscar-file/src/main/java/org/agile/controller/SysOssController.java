package org.agile.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.agile.common.ResultVo;
import org.agile.common.exception.RRException;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.validator.ValidatorUtils;
import org.agile.common.validator.group.AliyunGroup;
import org.agile.common.validator.group.QcloudGroup;
import org.agile.common.validator.group.QiniuGroup;
import org.agile.constant.ConfigConstant;
import org.agile.constant.Constant;
import org.agile.entity.SysOssEntity;
import org.agile.service.api.ISysConfigService;
import org.agile.service.api.ISysOssService;
import org.agile.utils.oss.CloudStorageConfig;
import org.agile.utils.oss.OSSFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * 文件上传
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private ISysOssService sysOssService;
    @Autowired
    private ISysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysOssEntity> sysOssList = sysOssService.queryList(query);
		int total = sysOssService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public ResultVo config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return ResultVo.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@RequestMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public ResultVo saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

		return ResultVo.ok();
	}
	

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public ResultVo upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String url = OSSFactory.build().upload(file.getBytes());

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return ResultVo.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public ResultVo delete(@RequestBody Long[] ids){
		sysOssService.deleteBatch(ids);

		return ResultVo.ok();
	}

}
