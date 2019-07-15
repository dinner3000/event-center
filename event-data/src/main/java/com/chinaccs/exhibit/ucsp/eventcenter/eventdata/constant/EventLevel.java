package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant;

import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer code;

    EventLevel(String name, Integer code){
        this.name = name;
        this.code = code;
    }

    public static List<Map<String, Object>> toPropertyMapList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EventLevel eventLevel : EventLevel.values()){
            Map<String, Object> item = new HashMap<>();
            item.put(Constant.LABEL_NAME, eventLevel.name);
            item.put(Constant.CODE_NAME, eventLevel.code);
            list.add(item);
        }
        return list;
    }

    private static HashMap<Integer,EventLevel> map = new HashMap<>();
    static {
        for (EventLevel d : EventLevel.values()){
            map.put(d.code, d);
        }
    }

    public static EventLevel parse(Integer code) {
        if(map.containsKey(code)){
            return map.get(code);
        }
        return null;
    }
}
