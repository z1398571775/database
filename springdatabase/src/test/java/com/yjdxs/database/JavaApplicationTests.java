package com.yjdxs.database;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class JavaApplicationTests {

    @Test
    void contextLoads() {
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
        List list1  = jdbcTemplate.queryForList("SHOW TABLES");
        System.out.println("TAG:"+list1.toString());
        List list2  = jdbcTemplate.queryForList("DESCRIBE user");
        System.out.println("TAG:"+list2.toString());
    }
}
