package com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils;

public class StringUtil {
    public static String getPercentString(Float input, Integer precision){
        if (precision == null){
            throw new RuntimeException("precision cannot be null");
        }
        if (precision < 0){
            throw new RuntimeException("precision cannot be smaller than 0");
        }

        String percentFormat = String.format("%%.%df%%%%", precision);

        return String.format(percentFormat, input * 100F);
    }

}
