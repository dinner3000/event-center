/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.common.exception;


import com.chinaccs.exhibit.ucsp.eventcenter.common.utils.MessageUtils;

/**
 * 自定义异常
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
public class EventCenterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public EventCenterException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public EventCenterException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public EventCenterException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public EventCenterException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public EventCenterException(String msg) {
		super(msg);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public EventCenterException(String msg, Throwable e) {
		super(msg, e);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}