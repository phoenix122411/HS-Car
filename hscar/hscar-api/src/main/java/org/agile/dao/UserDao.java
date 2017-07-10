package org.agile.dao;

import org.agile.entity.UserEntity;

/**
 * 用户
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
    
}
