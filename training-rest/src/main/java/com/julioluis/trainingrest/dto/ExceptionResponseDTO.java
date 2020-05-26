package com.julioluis.trainingrest.dto;

import java.util.Date;

public class ExceptionResponseDTO {

    private Date timeStamp;
    private String message;
    private String detail;

    public ExceptionResponseDTO(Date timeStamp, String message, String detail) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
