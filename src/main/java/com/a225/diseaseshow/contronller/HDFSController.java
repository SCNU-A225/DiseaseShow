package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.hadoop.HDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/hdfs")
public class HDFSController {

    @Autowired
    private HDFSClient hdfsClient;

    // 创建目录
    @RequestMapping(value = "/mkdir", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes mkdir(String path) throws InterruptedException, IOException, URISyntaxException {
        return hdfsClient.mkdir(path);
    }

    // 删除文件或目录
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes delete(String path) throws InterruptedException, IOException, URISyntaxException {
        return hdfsClient.delete(path);
    }

    // 修改文件名或目录名
    @RequestMapping(value = "/rename", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes rename(String name, String rename) throws InterruptedException, IOException, URISyntaxException {
        return hdfsClient.rename(name,rename);
    }

    // 文件列表查询
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes fileList(String path) throws InterruptedException, IOException, URISyntaxException {
        return hdfsClient.fileList(path);
    }
}
