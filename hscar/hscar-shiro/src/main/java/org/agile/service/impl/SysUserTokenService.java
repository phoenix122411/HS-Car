package org.agile.service.impl;

import java.util.Date;

import org.agile.common.ResultVo;
import org.agile.dao.SysUserTokenDao;
import org.agile.entity.SysUserTokenEntity;
import org.agile.service.api.ISysUserTokenService;
import org.agile.shiro.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysUserTokenService")
public class SysUserTokenService implements ISysUserTokenService {
	@Autowired
	private SysUserTokenDao sysUserTokenDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public SysUserTokenEntity queryByUserId(Long userId) {
		return sysUserTokenDao.queryByUserId(userId);
	}

	@Override
	public SysUserTokenEntity queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}

	@Override
	public void save(SysUserTokenEntity token){
		sysUserTokenDao.save(token);
	}
	
	@Override
	public void update(SysUserTokenEntity token){
		sysUserTokenDao.update(token);
	}

	@Override
	public ResultVo createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = queryByUserId(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			update(tokenEntity);
		}

		ResultVo r = ResultVo.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}
}
