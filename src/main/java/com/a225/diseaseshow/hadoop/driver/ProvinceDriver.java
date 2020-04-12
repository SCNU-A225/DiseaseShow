package com.a225.diseaseshow.hadoop.driver;

import com.a225.diseaseshow.bean.ProvinceBean;
import com.a225.diseaseshow.hadoop.format.ProvinceOutputFormat;
import com.a225.diseaseshow.hadoop.map.ProvinceMapper;
import com.a225.diseaseshow.hadoop.reduce.ProvinceReducer;
import com.a225.diseaseshow.utils.PropertiesUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ProvinceDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        String hdfsUri = (String) PropertiesUtils.getProperty("hdfs.uri");
        conf.set("fs.defaultFS", hdfsUri);
        Job job = Job.getInstance(conf);

        job.setJarByClass(ProvinceDriver.class);
        job.setMapperClass(ProvinceMapper.class);
        job.setReducerClass(ProvinceReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ProvinceBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ProvinceBean.class);

        job.setOutputFormatClass(ProvinceOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result = job.waitForCompletion(true);
    }
}
