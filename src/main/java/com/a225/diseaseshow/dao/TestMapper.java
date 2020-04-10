package com.a225.diseaseshow.dao;

import com.a225.diseaseshow.bean.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TestMapper {
    Test selectById(@Param("id") int id);
}
