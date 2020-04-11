package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.hadoop.HDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    // 文件上传
    // path 格式：hdfs路径名+文件全名
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes upload(MultipartFile file, String path) throws IOException, URISyntaxException, InterruptedException {
        if (file.isEmpty()) return new ResultRes(400,"文件为空");
        FileInputStream fis = (FileInputStream) file.getInputStream();
        return hdfsClient.upload(fis,path);
    }

    // 文件下载
    //path: 目标文件的hdfs目录，包括文件名
    //filename: 目标文件的文件名
    @RequestMapping(value = "download", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes download(String path, String filename, HttpServletResponse response) throws IOException, URISyntaxException, InterruptedException {
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        response.setContentType("multipart/form-data");
        OutputStream os = response.getOutputStream();
        return hdfsClient.download(os,path);
    }
}
