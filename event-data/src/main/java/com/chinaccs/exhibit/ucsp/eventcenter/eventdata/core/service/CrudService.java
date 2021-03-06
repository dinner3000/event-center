/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 * <p>
 *
 * <p>
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    List<T> list(QueryWrapper<T> queryWrapper);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}