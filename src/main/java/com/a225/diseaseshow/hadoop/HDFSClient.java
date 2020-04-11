package com.a225.diseaseshow.hadoop;

import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.utils.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HDFSClient {

    @Autowired
    HDFSUtil hdfsUtil;

    // 创建目录
    public ResultRes mkdir(String path) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        boolean success = fs.mkdirs(new Path(path));
        fs.close();
        if (success)
            return new ResultRes(200,"创建目录成功");
        return new ResultRes(400, "创建目录失败");
    }

    // 删除文件或目录
    public ResultRes delete(String path) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        boolean success = fs.delete(new Path(path), true);
        fs.close();
        if (success)
            return new ResultRes(200,"删除成功");
        return new ResultRes(400, "删除失败");
    }

    // 修改文件名或目录名
    public ResultRes rename(String name,String rename) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        boolean success = fs.rename(new Path(name), new Path(rename));
        fs.close();
        if (success)
            return new ResultRes(200,"命名成功");
        return new ResultRes(400, "命名失败");
    }

    // 获取目录内文件列表
    public ResultRes fileList(String path) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        List<String> list = new ArrayList<>();
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(path),true);
        while (listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();
            list.add(status.getPath().getName());
        }
        fs.close();
        return new ResultRes(200,"查询成功",list);
    }

    // 文件上传
    public ResultRes upload(FileInputStream fis, String path) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        FSDataOutputStream fos = fs.create(new Path(path));
        IOUtils.copyBytes(fis,fos,new Configuration());
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
        return new ResultRes(200,"上传成功");
    }

    // 文件下载
    // os为response的输出流
    public ResultRes download(OutputStream os,String path) throws InterruptedException, IOException, URISyntaxException {
        FileSystem fs = hdfsUtil.getHDFS();
        FSDataInputStream fis = fs.open(new Path(path));
        IOUtils.copyBytes(fis,os,new Configuration());
        IOUtils.closeStream(fis);
        fis.close();
        return new ResultRes(200,"下载成功");
    }
}
