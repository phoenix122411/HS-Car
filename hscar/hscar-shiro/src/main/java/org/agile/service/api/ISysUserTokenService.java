package org.agile.service.api;

import org.agile.common.ResultVo;
import org.agile.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface ISysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
	
	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId 用户ID
	 */
	ResultVo createToken(long userId);

}
