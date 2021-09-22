package com.yjdxs.database.System.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4514429917653557751L;
    private int code;
    private String message;
    private T Data;
    private boolean isSuccess;

    public void error(String message){
        this.code = 500;
        this.message = message;
        this.isSuccess = false;
    }

    public void success(String message){
        this.code = 200;
        this.message = message;
        this.isSuccess = true;
    }
}
