package com.tsfeng.cn.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/24 15:22
 */
public class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    private DataSource dataSource;

    public static String DB_DRUID_PROPERTIES = "jdbc.properties";

    private interface Singleton {
        ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private ConnectionFactory() {
        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        properties.setProperty("url", "jdbc:mysql://localhost:5566/study?characterEncoding=utf8&serverTimezone=UTC&useSSL=true");
        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }

}
