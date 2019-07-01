package com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaccs.exhibit.ucsp.eventcenter.common.service.impl.CrudServiceImpl;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dao.ForwardConfigDao;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.dto.ForwardConfigDTO;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.entity.ForwardConfigEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.modules.common.service.ForwardConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 事件推送配置 
 *
 * @author Gary.Z zhujiawei@sunseaaiot.com
 * @since 1.0.0 2019-07-01
 */
@Service
public class ForwardConfigServiceImpl extends CrudServiceImpl<ForwardConfigDao, ForwardConfigEntity, ForwardConfigDTO> implements ForwardConfigService {

    @Override
    public QueryWrapper<ForwardConfigEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ForwardConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}