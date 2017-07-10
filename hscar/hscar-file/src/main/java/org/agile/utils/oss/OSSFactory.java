package org.agile.utils.oss;

import org.agile.common.utils.SpringContextUtils;
import org.agile.constant.ConfigConstant;
import org.agile.constant.Constant;
import org.agile.service.api.ISysConfigService;

/**
 * 文件上传Factory
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public final class OSSFactory {
    private static ISysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (ISysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
