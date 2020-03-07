package com.julioluis.trainingrest.utils;

public enum StatusEnum {
    ACTIVE(1),
    INACTIVE(2),
    REGISTERED(3),
    UNREGISTERED(4);

    StatusEnum(Integer status) {
        this.status=status;
    }

    private Integer status;


    public Integer getStatus() {
        return status;
    }
}
