package com.a225.diseaseshow.service;

import com.a225.diseaseshow.bean.Test;
import com.a225.diseaseshow.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public Test selectById(int id){
        return testMapper.selectById(id);
    }
}
