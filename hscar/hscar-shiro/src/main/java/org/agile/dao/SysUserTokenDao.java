package org.agile.dao;

import org.agile.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    
    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);
	
}
