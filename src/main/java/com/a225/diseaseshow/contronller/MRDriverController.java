package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.hadoop.driver.ProvinceDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/driver")
public class MRDriverController {

    // 每个省的病历统计
    // dataPath:文件在hdfs的路径
    // outputPath: hdfs输出路径
    @RequestMapping(value = "provincedriver", method = RequestMethod.POST)
    @ResponseBody
    public ResultRes ProvinceDriver(String dataPath, String outputPath) throws IOException, InterruptedException, ClassNotFoundException {
        ProvinceDriver provinceDriver = new ProvinceDriver();
        provinceDriver.main(new String[]{dataPath,outputPath});
        return new ResultRes(200,"成功");
    }
}
