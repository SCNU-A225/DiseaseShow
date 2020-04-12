package com.a225.diseaseshow.hadoop.format;

import com.a225.diseaseshow.bean.ProvinceBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ProvinceOutputFormat extends FileOutputFormat<Text, ProvinceBean> {
    @Override
    public RecordWriter<Text, ProvinceBean> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new ProvinceRecordWriter(taskAttemptContext);
    }
}
