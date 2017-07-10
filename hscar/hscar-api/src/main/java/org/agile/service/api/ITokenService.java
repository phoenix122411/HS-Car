package org.agile.service.api;

import java.util.Map;

import org.agile.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ITokenService {

	TokenEntity queryByUserId(Long userId);

	TokenEntity queryByToken(String token);
	
	void save(TokenEntity token);
	
	void update(TokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId);

}
