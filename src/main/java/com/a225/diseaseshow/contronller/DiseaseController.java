package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.DiseaseBean;
import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    //根据病名模糊查询返回病名列表
    @RequestMapping(value = "/selectNames", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes selectNames(String name){
        List<String> list = diseaseService.selectNames(name);
        return new ResultRes(200,"查询成功",list);
    }

    //根据病名查询详细
    @RequestMapping(value = "/selectByName", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes selectByName(String name){
        DiseaseBean bean = diseaseService.selectByName(name);
        return new ResultRes(200,"查询成功",bean);
    }

    //根据症状模糊查询返回病名列表
    @RequestMapping(value = "/symptom", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes selectSymptom(String symptom){
        List<String> list = diseaseService.selectSymptom(symptom);
        return new ResultRes(200,"查询成功",list);
    }
}
