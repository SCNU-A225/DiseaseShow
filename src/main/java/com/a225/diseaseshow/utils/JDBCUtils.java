package com.a225.diseaseshow.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * JDBC的工具类，封装了jdbc的一些方法
 */
public class JDBCUtils {

    //关闭jdbc的链接
    public static void release(PreparedStatement ps, Connection conn){
        try{
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void release(ResultSet result,PreparedStatement ps, Connection conn){
        try {
            if(result != null){
                result.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally{
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(conn != null){
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //获取jdbc的链接
    public static Connection getConnetions() {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = (String) PropertiesUtils.getProperty("spring.datasource.url");
        String user = (String) PropertiesUtils.getProperty("spring.datasource.username");
        String password = (String) PropertiesUtils.getProperty("spring.datasource.password");
        try {
            //注册驱动程序
            Class.forName(driver);
            //实际应该这样写(由于对应的应用程序中有一个对应的静态代码块，自动回将驱动的类对象进行驱动加载)
            //DriverManager.registerDriver((Driver) Class.forName(driverClass).newInstance());

            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}