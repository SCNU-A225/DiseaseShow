package com.a225.diseaseshow.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@ConfigurationProperties(prefix = "hdfs")
public class HDFSUtil {

    private String uri;
    private String user;

    public FileSystem getHDFS() throws URISyntaxException, IOException, InterruptedException {
        // 1 获取文件系统
        Configuration conf = new Configuration();
        // 2 配置集群
        FileSystem fs = FileSystem.get(new URI(uri), conf, user);
        return fs;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
