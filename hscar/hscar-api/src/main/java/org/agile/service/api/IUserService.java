package org.agile.service.api;

import java.util.List;
import java.util.Map;

import org.agile.entity.UserEntity;

/**
 * 用户
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface IUserService {

	UserEntity queryObject(Long userId);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void save(String mobile, String password);
	
	void update(UserEntity user);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	long login(String mobile, String password);
}
