package org.agile.dao;

import org.agile.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
