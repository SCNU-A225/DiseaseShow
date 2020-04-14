package com.a225.diseaseshow.service;

import com.a225.diseaseshow.bean.DayCountBean;
import com.a225.diseaseshow.dao.DayCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayCountService {

    @Autowired
    private DayCountMapper dayCountMapper;

    public List<DayCountBean> selectAll(){
        return dayCountMapper.selectAll();
    }
}
