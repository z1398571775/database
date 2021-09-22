package com.yjdxs.database.dataSource.contraller;

import com.yjdxs.database.System.vo.Result;
import com.yjdxs.database.dataSource.entity.DataBase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class DataSourceController {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();


    @PostMapping("/setDataSource")
    public Result<String> setDataSource(){
        Result<String> result = new Result<>();
        try {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceBuilder.username("root");
            dataSourceBuilder.password("root");
            dataSourceBuilder.url("jdbc:mysql://47.103.79.58:3306/?useSSL=false&serverTimezone=UTC");
            DataSource dataSource = dataSourceBuilder.build();
            dataSource.getConnection();
            jdbcTemplate.setDataSource(dataSource);
            result.success("连接成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            result.error("连接失败");
        }
        return result;
    }

    /***
     * 获取数据库中所有的数据库名字
     * @return
     */
    @PostMapping("/getDataBases")
    public Result<List> getDataBases(){
        Result<List> result = new Result<>();
        result.setData(jdbcTemplate.queryForList("show databases"));
        return result;
    }

    /**
     * 选择juku
     * @param dataBase
     * @return
     */
    @PostMapping("/setDataBase")
    public Result<List> setDataBase(@RequestBody DataBase dataBase){
        Result<List> result = new Result<>();
        jdbcTemplate.execute("use "+dataBase.getDatabaseName());
        result.setData(jdbcTemplate.queryForList("SHOW TABLES"));
        return result;
    }

    /**
     * 选择juku
     * @param dataBase
     * @return
     */
    @PostMapping("/setTable")
    public Result<List> setTable(@RequestBody DataBase dataBase){
        Result<List> result = new Result<>();
        jdbcTemplate.execute("use "+dataBase.getDatabaseName());
        result.setData(jdbcTemplate.queryForList("SHOW TABLES"));
        return result;
    }
}
