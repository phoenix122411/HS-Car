package org.agile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agile.common.ResultVo;
import org.agile.constant.Constant.SupportOrNot;
import org.agile.constant.Constant.YesOrNo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统常量获取
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-10
 */
@Controller
@RequestMapping("/sys/constant")
public class SysConstantController {
	
	/**
	 * 获取是或否的枚举列表
	 */
	@ResponseBody
	@RequestMapping("/get_yes_or_no_list")
	public ResultVo get_yes_or_no_list() {
		List<Map> yesOrNoList = new ArrayList<Map>();
		
		YesOrNo[] enums = YesOrNo.values();
		for(int i=0; i<enums.length; i++) {
			Map map = new HashMap();
			map.put("code", enums[i].getCode());
			map.put("display", enums[i].getDisplay());
			yesOrNoList.add(map);
		}
		
		return ResultVo.ok().put("yesOrNoList", yesOrNoList);
	}
	
	/**
	 * 获取支持或不支持的枚举列表
	 */
	@ResponseBody
	@RequestMapping("/get_support_or_not_list")
	public ResultVo get_support_or_not_list() {
		List<Map> supportOrNotList = new ArrayList<Map>();
		
		SupportOrNot[] enums = SupportOrNot.values();
		for(int i=0; i<enums.length; i++) {
			Map map = new HashMap();
			map.put("code", enums[i].getCode());
			map.put("display", enums[i].getDisplay());
			supportOrNotList.add(map);
		}
		
		return ResultVo.ok().put("supportOrNotList", supportOrNotList);
	}
	
}