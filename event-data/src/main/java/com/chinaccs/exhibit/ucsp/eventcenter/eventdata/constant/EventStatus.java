package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EventStatus {
    INIT("待确认", 0),
    CONFIRMED("已确认", 1),
    RESOLVING("处理中", 2),
    RESOLVED("处理完成", 3),
    REJECTED("驳回", 4)
    ;

    private String name;

    private Integer value;

    EventStatus(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public static List<Map<String, Object>> toUIDropListItemDTOList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EventStatus eventStatus : EventStatus.values()){
            Map<String, Object> item = new HashMap<>();
            item.put(Constant.LABEL_NAME, eventStatus.name);
            item.put(Constant.VALUE_NAME, eventStatus.value);
            list.add(item);
        }
        return list;
    }
}
