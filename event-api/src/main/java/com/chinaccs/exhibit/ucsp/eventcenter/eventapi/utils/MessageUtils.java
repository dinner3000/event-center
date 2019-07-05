/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils;

import com.chinaccs.exhibit.ucsp.eventcenter.common.utils.SpringContextUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 * @since 1.0.0
 */
public class MessageUtils {
    private static MessageSource messageSource;
    static {
        messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
    }

    public static String getMessage(int code){
        return getMessage(code, new String[0]);
    }

    public static String getMessage(int code, String... params){
        return messageSource.getMessage(code+"", params, LocaleContextHolder.getLocale());
    }
}
