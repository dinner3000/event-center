package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EventForwardType {

    SMS("短信", 0),
    PORTAL("Portal", 1),
    APP("App", 2)
    ;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer code;

    EventForwardType(String name, Integer code){
        this.name = name;
        this.code = code;
    }

    public static List<Map<String, Object>> toPropertyMapList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EventForwardType eventLevel : EventForwardType.values()){
            Map<String, Object> item = new HashMap<>();
            item.put(Constant.LABEL_NAME, eventLevel.name);
            item.put(Constant.CODE_NAME, eventLevel.code);
            list.add(item);
        }
        return list;
    }

    private static HashMap<Integer,EventForwardType> map = new HashMap<>();
    static {
        for (EventForwardType d : EventForwardType.values()){
            map.put(d.code, d);
        }
    }

    public static EventForwardType parse(Integer code) {
        if(map.containsKey(code)){
            return map.get(code);
        }
        return null;
    }
}
