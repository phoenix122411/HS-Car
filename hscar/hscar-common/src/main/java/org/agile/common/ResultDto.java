package org.agile.common;

import java.io.Serializable;

/**
 * Controller层和Service层之间的返回数据
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class ResultDto implements Serializable {
	private static final long serialVersionUID = -7763455083046684919L;

	/**
	 * 执行状态
	 */
	private boolean state;
	
	/**
	 * 执行编码
	 */
	private String code = "0";
	
	/**
	 * 返回的信息
	 */
	private String message;
	
	/**
	 * 返回的业务数据
	 */
	private Object data;
	
	/**
	 * @param state 执行状态
	 * @param message 返回的信息
	 */
	public ResultDto(boolean state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	
	/**
	 * @param message 返回的信息
	 */
	public ResultDto(String message) {
		this(true, message);
	}
	
	/**
	 * @param state 执行状态
	 * @param data 返回的业务数据
	 */
	public ResultDto(boolean state, Object data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	/**
	 * @param state 执行状态
	 * @param message 返回的信息
	 * @param data 返回的业务数据
	 */
	public ResultDto(boolean state, String message, Object data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}