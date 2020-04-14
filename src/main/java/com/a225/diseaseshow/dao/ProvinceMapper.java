package com.a225.diseaseshow.dao;

import com.a225.diseaseshow.bean.ProvinceBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProvinceMapper {

    List<ProvinceBean> selectAll();

}
