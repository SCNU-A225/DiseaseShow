package com.a225.diseaseshow.hadoop.map;

import com.a225.diseaseshow.bean.ProvinceBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ProvinceMapper extends Mapper<LongWritable,Text,Text,ProvinceBean> {
    ProvinceBean v = new ProvinceBean();
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行
        String line = value.toString();
        // 2 切割字段
        //date,province_code,province,city_code,city,confirmed,suspected,cured,dead
        //20191201,420000,湖北省,420100,武汉市,1,0,0,0
        String[] fields = line.split(",");
        // 3 封装对象
        String province = fields[2];
        int sum = Integer.parseInt(fields[5]);
        int cured = Integer.parseInt(fields[7]);
        int dead = Integer.parseInt(fields[8]);
        k.set(province);
        v.setProvince(province);
        v.setSum(sum);
        v.setCured(cured);
        v.setDead(dead);
        // 4 写出
        context.write(k,v);
    }
}
