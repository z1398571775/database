package com.yjdxs.database.test.controller;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test")
    public List  getSession(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        dataSourceBuilder.url("jdbc:mysql://47.103.79.58:3306/?useSSL=false&serverTimezone=UTC");
        DataSource dataSource = dataSourceBuilder.build();
        try {
            dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List list  = jdbcTemplate.queryForList("show databases");
        System.out.println("TAG:"+list.toString());
        jdbcTemplate.execute("use mysql");
        List list1  = jdbcTemplate.queryForList("select * from user ");
        System.out.println("TAG:"+list1.toString());
        return list1;
    }
}
