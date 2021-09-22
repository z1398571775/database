package com.yjdxs.database.dataSource.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataBase implements Serializable {

    private static final long serialVersionUID = 5173902007523690161L;

    private String userName;

    private String password;

    private String port;

    private String url;

    private String databaseName;

    private String tableName;

}
