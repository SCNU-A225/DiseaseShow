package com.a225.diseaseshow.dao;

import com.a225.diseaseshow.bean.DiseaseBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DiseaseMapper {

    // 模糊查询病名
    List<String> selectNames(@Param("name") String name);

    // 模糊查询症状，返回病名
    List<String> selectSymptom(@Param("symptom") String symptom);

    // 根据病名查找详情
    DiseaseBean selectByName(@Param("name") String name);
}
