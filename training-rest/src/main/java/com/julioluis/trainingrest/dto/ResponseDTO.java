package com.julioluis.trainingrest.dto;

import java.util.List;

public class ResponseDTO<T> {
    private T entity;
    private List<T> entities;
    private boolean success;
    private String message;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ResponseDTO<T> populateSuccessDto(T entity) {
        this.setSuccess(Boolean.TRUE);
        this.setEntity(entity);
        return this;
    }

    public void populateFailureDto(String message) {
        this.setMessage(message);
        this.setSuccess(Boolean.FALSE);
    }

}
