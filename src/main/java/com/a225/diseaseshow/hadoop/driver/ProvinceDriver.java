package com.a225.diseaseshow.hadoop.driver;

import com.a225.diseaseshow.bean.ProvinceBean;
import com.a225.diseaseshow.hadoop.format.ProvinceOutputFormat;
import com.a225.diseaseshow.hadoop.map.ProvinceMapper;
import com.a225.diseaseshow.hadoop.reduce.ProvinceReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ProvinceDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        args = new String[]{"E:/hadoopTestFile/province.csv","E:/hadoopTestFile/output"};


        args = new String[]{"/disease/province.csv","/disease/output"};
        //args = new String[]{"D:/test/hadoop_test/province.csv","D:/test/hadoop_test/output"};
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.0.110:9000");
        //conf.set("hadoop.tmp.dir","/opt/module/hadoop-2.7.2/data/tmp");
        System.setProperty("HADOOP_USER_NAME", "root");
        //System.setProperty("user.name", "root");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "192.168.0.111");
        conf.set("mapreduce.app-submission.cross-platform", "true");
        conf.set("mapreduce.job.jar","src\\main\\resources\\com.jar");


        Job job = Job.getInstance(conf);

        job.setJarByClass(ProvinceDriver.class);
        job.setMapperClass(ProvinceMapper.class);
        job.setReducerClass(ProvinceReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ProvinceBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ProvinceBean.class);

        //job.setOutputFormatClass(ProvinceOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean result = job.waitForCompletion(true);
    }
}
