package com.a225.diseaseshow.service;

import com.a225.diseaseshow.bean.DiseaseBean;
import com.a225.diseaseshow.dao.DiseaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    @Autowired
    private DiseaseMapper diseaseMapper;

    public List<String> selectNames(String name){
        return diseaseMapper.selectNames(name);
    }

    public DiseaseBean selectByName(String name){
        return diseaseMapper.selectByName(name);
    }

    public List<String> selectSymptom(String symptom){
        return diseaseMapper.selectSymptom(symptom);
    }
}
