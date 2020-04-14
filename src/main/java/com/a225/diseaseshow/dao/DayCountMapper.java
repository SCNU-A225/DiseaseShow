package com.a225.diseaseshow.dao;

import com.a225.diseaseshow.bean.DayCountBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DayCountMapper {
    List<DayCountBean> selectAll();
}
