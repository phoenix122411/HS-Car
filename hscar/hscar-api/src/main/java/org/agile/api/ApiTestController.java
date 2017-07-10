package org.agile.api;

import org.agile.annotation.IgnoreAuth;
import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * API测试接口
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/api")
@Api("测试接口")
public class ApiTestController {

    /**
     * 获取用户信息
     */
	@GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
    public ResultVo userInfo(@LoginUser UserEntity user) {
        return ResultVo.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @IgnoreAuth
    @GetMapping("notToken")
    @ApiOperation(value = "忽略Token验证测试")
    public ResultVo notToken() {
        return ResultVo.ok().put("message", "无需token也能访问。。。");
    }
}
