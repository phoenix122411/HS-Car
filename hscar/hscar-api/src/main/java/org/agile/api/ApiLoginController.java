package org.agile.api;

import java.util.Map;

import org.agile.annotation.IgnoreAuth;
import org.agile.common.ResultVo;
import org.agile.common.validator.Assert;
import org.agile.service.api.ITokenService;
import org.agile.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * API登录授权
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/api")
@Api("登录接口")
public class ApiLoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "登录",notes = "登录说明")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public ResultVo login(String mobile, String password) {
    	Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return ResultVo.ok(map);
    }

}
