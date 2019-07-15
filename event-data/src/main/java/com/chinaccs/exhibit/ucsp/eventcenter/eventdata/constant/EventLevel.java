package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EventLevel {

    NORMAL("正常", 0),
    WARN_1("轻度警告", 1),
    WARN_2("中度警告", 2),
    WARN_3("重度警告", 3)
    ;

    private String name;

    private Integer value;

    EventLevel(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public static List<Map<String, Object>> toUIDropListItemDTOList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EventLevel eventLevel : EventLevel.values()){
            Map<String, Object> item = new HashMap<>();
            item.put(Constant.LABEL_NAME, eventLevel.name);
            item.put(Constant.VALUE_NAME, eventLevel.value);
            list.add(item);
        }
        return list;
    }
}
