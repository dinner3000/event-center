/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {
	
}