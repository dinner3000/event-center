/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.exception;

import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.ErrorCode;
import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.EventCenterException;
import com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 * @since 1.0.0
 */
@RestControllerAdvice
public class EventCenterExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(EventCenterExceptionHandler.class);

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(EventCenterException.class)
	public Result handleEventCenterException(EventCenterException ex){
		Result result = new Result();
		result.error(ex.getCode(), ex.getMsg());

		return result;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException ex){
		Result result = new Result();
		result.error(ErrorCode.DB_RECORD_EXISTS);

		return result;
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex){
		logger.error(ex.getMessage(), ex);

		return new Result().error();
	}
}