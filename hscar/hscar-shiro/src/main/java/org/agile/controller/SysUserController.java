package org.agile.controller;

import java.util.List;
import java.util.Map;

import org.agile.annotation.SysLog;
import org.agile.common.ResultVo;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.validator.Assert;
import org.agile.common.validator.ValidatorUtils;
import org.agile.common.validator.group.AddGroup;
import org.agile.common.validator.group.UpdateGroup;
import org.agile.constant.Constant;
import org.agile.entity.SysUserEntity;
import org.agile.service.api.ISysUserRoleService;
import org.agile.service.api.ISysUserService;
import org.agile.utils.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResultVo list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//查询列表数据
		Query query = new Query(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public ResultVo info(){
		return ResultVo.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public ResultVo password(String password, String newPassword){
		Assert.isBlank(newPassword, "新密码不为能空");
		
		//sha256加密
		password = new Sha256Hash(password).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();
				
		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return ResultVo.error("原密码不正确");
		}
		
		//退出
		ShiroUtils.logout();
		
		return ResultVo.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ResultVo info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return ResultVo.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ResultVo save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public ResultVo update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ResultVo delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ResultVo.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return ResultVo.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return ResultVo.ok();
	}
}
