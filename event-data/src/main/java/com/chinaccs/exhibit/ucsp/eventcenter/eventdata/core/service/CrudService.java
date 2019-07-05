/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.page.PageData;

import java.util.List;
import java.util.Map;

/**
 *  CRUD基础服务接口
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 */
public interface CrudService<T, D> extends BaseService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}