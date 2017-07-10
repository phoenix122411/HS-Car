package org.agile.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.agile.common.ResultVo;
import org.agile.entity.SysUserEntity;
import org.agile.service.impl.SysUserService;
import org.agile.service.impl.SysUserTokenService;
import org.agile.utils.ShiroUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 登录相关
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Controller
public class SysLoginController {
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public ResultVo login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return ResultVo.error("验证码不正确");
		}
		
		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(password).toHex())) {
			return ResultVo.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return ResultVo.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		ResultVo result = sysUserTokenService.createToken(user.getUserId());
		
//		try{
//			Subject subject = ShiroUtils.getSubject();
//			password = new Sha256Hash(password).toHex(); // sha256加密
//			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//			subject.login(token);
//		}catch (UnknownAccountException e) {
//			return ResultVo.error(e.getMessage());
//		}catch (IncorrectCredentialsException e) {
//			return ResultVo.error(e.getMessage());
//		}catch (LockedAccountException e) {
//			return ResultVo.error(e.getMessage());
//		}catch (AuthenticationException e) {
//			return ResultVo.error("账户验证失败");
//		}
	    
		return result;
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:login.html";
	}
	
}
