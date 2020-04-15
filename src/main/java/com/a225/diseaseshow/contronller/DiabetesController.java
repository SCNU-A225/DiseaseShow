package com.a225.diseaseshow.contronller;

import com.a225.diseaseshow.bean.ResultRes;
import com.a225.diseaseshow.service.DiabetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: DiabetesController
 * @Description: 糖尿病预测控制器
 * @Author: jiangjian
 * @CreateDate: 2020/4/15 21:14
 * @UpdateUser: jiangjian
 * @UpdateDate: 2020/4/15 21:14
 * @UpdateRemark: TODO
 * @Version: V1.0
 */
@Controller
@RequestMapping("/diabetes")
public class DiabetesController {
    @Autowired
    private DiabetesService diabetesService;

    @RequestMapping("/predict")
    @ResponseBody
    //妊娠、血糖、血压、皮肤厚度、胰岛素、体重指数、糖尿病患者的功能、年龄
    public ResultRes predict(int Pregnancies, int Glucose, int BloodPressure, int SkinThickness, int Insulin, double BMI, double DiabetesPedigreeFunction, int Age) {
        try {
            boolean predict = diabetesService.predict(Pregnancies,Glucose,BloodPressure,SkinThickness,Insulin,BMI,DiabetesPedigreeFunction,Age);
            return new ResultRes(200,"成功", predict);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultRes(400,e.getMessage(), e);
        }
    }
}
