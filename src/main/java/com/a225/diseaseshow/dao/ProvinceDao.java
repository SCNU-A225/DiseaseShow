package com.a225.diseaseshow.dao;

import com.a225.diseaseshow.bean.ProvinceBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ProvinceDao {
    void insert(@Param("bean") ProvinceBean bean);
    //truncate table table_name;
    void truncate();
}
