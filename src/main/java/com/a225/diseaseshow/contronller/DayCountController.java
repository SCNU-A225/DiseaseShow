package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.DayCountBean;
import com.a225.diseaseshow.service.DayCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/daycount")
public class DayCountController {

    @Autowired
    private DayCountService dayCountService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public List<DayCountBean> selectAll(){
        return dayCountService.selectAll();
    }
}
