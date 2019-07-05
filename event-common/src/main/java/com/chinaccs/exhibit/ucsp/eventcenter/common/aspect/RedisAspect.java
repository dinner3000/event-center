/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.common.aspect;

import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.ErrorCode;
import com.chinaccs.exhibit.ucsp.eventcenter.common.exception.EventCenterException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis切面处理类
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
@Aspect
@Component
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 是否开启redis缓存  true开启   false关闭
     */
    @Value("${cache.redis.open: false}")
    private boolean open;

    @Around("execution(* com.chinaccs.exhibit.ucsp.eventcenter.common.redis.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new EventCenterException(ErrorCode.REDIS_ERROR);
            }
        }
        return result;
    }
}
