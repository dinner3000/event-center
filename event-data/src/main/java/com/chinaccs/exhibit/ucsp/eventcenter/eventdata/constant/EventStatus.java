package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EventStatus {
    INIT("待确定", 0),
    CONFIRMED("已确定", 1),
    PADDING("待处置", 2),
    RESOLVING("处置中", 3),
    RESOLVED("处置结束", 4),
    REJECTED("已驳回", 5)
    ;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer code;

    EventStatus(String name, Integer code){
        this.name = name;
        this.code = code;
    }

    public static List<Map<String, Object>> toPropertyMapList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EventStatus eventStatus : EventStatus.values()){
            Map<String, Object> item = new HashMap<>();
            item.put(Constant.LABEL_NAME, eventStatus.name);
            item.put(Constant.CODE_NAME, eventStatus.code);
            list.add(item);
        }
        return list;
    }

    private static HashMap<Integer,EventStatus> map = new HashMap<>();
    static {
        for (EventStatus d : EventStatus.values()){
            map.put(d.code, d);
        }
    }

    public static boolean isValidCode(Integer code){
        return map.containsKey(code);
    }

    public static EventStatus parse(Integer code) {
        if(map.containsKey(code)){
            return map.get(code);
        }
        return null;
    }
}
