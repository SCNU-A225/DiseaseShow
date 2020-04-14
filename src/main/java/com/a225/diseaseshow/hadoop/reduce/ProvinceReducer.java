package com.a225.diseaseshow.hadoop.reduce;

import com.a225.diseaseshow.bean.ProvinceBean;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ProvinceReducer extends Reducer<Text,ProvinceBean,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<ProvinceBean> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        int cured = 0;
        int dead = 0;
        // 1 遍历所有bean进行统计
        for (ProvinceBean bean : values){
            sum += bean.getSum();
            cured += bean.getCured();
            dead += bean.getDead();
        }
        // 2 封装对象
        ProvinceBean bean = new ProvinceBean(key.toString(),sum,cured,dead);
        // 3 写出
        context.write(key,new IntWritable(sum));
    }
}
