package com.a225.diseaseshow.hadoop.format;

import com.a225.diseaseshow.bean.ProvinceBean;
import com.a225.diseaseshow.dao.ProvinceDao;
import com.a225.diseaseshow.utils.JDBCUtils;
import com.a225.diseaseshow.utils.PropertiesUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProvinceRecordWriter extends RecordWriter<Text, ProvinceBean> {

    private Connection conn;

    public ProvinceRecordWriter(TaskAttemptContext job){
        conn = JDBCUtils.getConnetions();
    }

    @Override
    public void write(Text text, ProvinceBean provinceBean) throws IOException, InterruptedException {
        String sql = "INSERT INTO provinces(province,sum,cured,dead) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,provinceBean.getProvince());
            preparedStatement.setInt(2,provinceBean.getSum());
            preparedStatement.setInt(3,provinceBean.getCured());
            preparedStatement.setInt(4,provinceBean.getDead());
            preparedStatement.executeUpdate();
            JDBCUtils.release(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

    }
}
