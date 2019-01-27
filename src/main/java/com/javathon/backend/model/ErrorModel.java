package com.javathon.backend.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class ErrorModel {
    String field;
    String errorCode;
    String msg;

    public ErrorModel(String field, String errorCode, String msg) {
        this.field = field;
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
