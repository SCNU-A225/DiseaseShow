package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.Test;
import com.a225.diseaseshow.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Test selectById(int id){
        return testService.selectById(id);
    }
}
