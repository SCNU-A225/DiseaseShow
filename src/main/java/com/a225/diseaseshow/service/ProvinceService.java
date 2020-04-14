package com.a225.diseaseshow.service;

import com.a225.diseaseshow.bean.ProvinceBean;
import com.a225.diseaseshow.dao.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    public List<ProvinceBean> selectAll(){
        return provinceMapper.selectAll();
    }
}
