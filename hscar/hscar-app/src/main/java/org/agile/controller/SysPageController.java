package org.agile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Controller
public class SysPageController {
	
	@RequestMapping("{module}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url){
		return module + "/" + url + ".html";
	}
	
	@RequestMapping("{system}/{module}/{url}.html")
	public String page(@PathVariable("system") String system, @PathVariable("module") String module, @PathVariable("url") String url){
		return system + "/" + module + "/" + url + ".html";
	}
}
