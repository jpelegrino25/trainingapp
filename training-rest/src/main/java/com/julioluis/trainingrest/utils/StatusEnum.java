package com.julioluis.trainingrest.utils;

public enum StatusEnum {
    ACTIVE(1,"Active"),
    INACTIVE(2,"Inactive"),
    REGISTERED(3,"Registered"),
    UNREGISTERED(4,"Unregistered");

    StatusEnum(Integer status,String description) {
        this.status=status;
        this.description=description;
    }

    private Integer status;
    private  String description;

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
