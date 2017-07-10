package org.agile.api;

import org.agile.annotation.IgnoreAuth;
import org.agile.common.ResultVo;
import org.agile.common.validator.Assert;
import org.agile.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 注册
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/api")
@Api("注册接口")
public class ApiRegisterController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping("register")
    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
            @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public ResultVo register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);

        return ResultVo.ok();
    }
}
