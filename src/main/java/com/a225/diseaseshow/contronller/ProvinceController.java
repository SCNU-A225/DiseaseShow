package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.ProvinceBean;
import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public ResultRes selectAll(){
        List<ProvinceBean> list = provinceService.selectAll();
        return new ResultRes(200,"查询成功",list);
    }
}
