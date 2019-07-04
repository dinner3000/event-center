package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.constant;

import lombok.Getter;

public enum EventForwardStatus {
    INITIAL(0),
    SUCCESS(1),
    FAILURE(-1);

    @Getter
    private Integer value;

    EventForwardStatus (Integer val){
        this.value = val;
    }
}
