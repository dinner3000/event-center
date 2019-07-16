package com.chinaccs.exhibit.ucsp.eventcenter.eventapi.utils;

import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class CollectionUtils {
    public static Map<String, Object> buildLabelCodeItem(Object val, String label){
        Map<String, Object> item = new HashMap<>();
        item.put(Constant.LABEL_NAME, label);
        item.put(Constant.CODE_NAME, val);
        return item;
    }

    public static Map<String, Object> buildLabelValueItem(Object val, String label){
        Map<String, Object> item = new HashMap<>();
        item.put(Constant.LABEL_NAME, label);
        item.put(Constant.VALUE_NAME, val);
        return item;
    }

//    public static Map<String, Object> buildTrendChartValueItem(Object val, String time, Integer overtime){
//        Map<String, Object> item = new HashMap<>();
//        item.put(Constant.CODE_NAME, val);
//        item.put(Constant.TIME_NAME, time);
//        item.put(Constant.TYPE_NAME, type);
//        return item;
//    }
}
