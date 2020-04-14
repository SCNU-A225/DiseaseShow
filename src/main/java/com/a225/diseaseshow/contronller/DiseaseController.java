package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.DiseaseBean;
import com.a225.diseaseshow.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @RequestMapping(value = "/selectNames", method = RequestMethod.POST)
    @ResponseBody
    public List<String> selectNames(String name){
        return diseaseService.selectNames(name);
    }

    @RequestMapping(value = "/selectByName", method = RequestMethod.POST)
    @ResponseBody
    public DiseaseBean selectByName(String name){
        return diseaseService.selectByName(name);
    }

    @RequestMapping(value = "/symptom", method = RequestMethod.POST)
    @ResponseBody
    public List<String> selectSymptom(String symptom){
        return diseaseService.selectSymptom(symptom);
    }
}
