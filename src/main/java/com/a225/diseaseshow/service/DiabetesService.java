package com.a225.diseaseshow.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * @ClassName: DiabetesService
 * @Description: 糖尿病预测服务
 * @Author: jiangjian
 * @CreateDate: 2020/4/15 22:16
 * @UpdateUser: jiangjian
 * @UpdateDate: 2020/4/15 22:16
 * @UpdateRemark: TODO
 * @Version: V1.0
 */
@Service
public class DiabetesService {
    private static final Log logger = LogFactory.getLog(DiabetesService.class);
    private static Classifier classifier = null;
    private static final ArrayList<Attribute> attributesList = new ArrayList<Attribute>();
    private static Instances blankInstances = null;

    private void initModel() throws Exception {
        logger.info("初始化糖尿病预测model");

        //读入数据集
        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setSource(DiabetesService.class.getResourceAsStream("/data/diabetes.arff"));
        Instances dataSet = arffLoader.getDataSet();
        dataSet.setClass(dataSet.attribute(dataSet.numAttributes() - 1));

        //新建分类器
        classifier = new SimpleLogistic();
        classifier.buildClassifier(dataSet);

        //建立空instances方便创建空instance
        for (int i = 0; i < dataSet.numAttributes(); i++) {
            attributesList.add(dataSet.attribute(i));
        }
        blankInstances = new Instances("data", attributesList, 0);
        blankInstances.setClassIndex(dataSet.numAttributes() - 1);

        //验证
        Evaluation evaluation = new Evaluation(dataSet);
        int length = dataSet.numInstances();
        for (int i = 0; i < length; i++) {
            Instance testInst = dataSet.instance(i);
            evaluation.evaluateModelOnceAndRecordPrediction(classifier, testInst);
        }

        logger.info("模型正确率：" + (1 - evaluation.errorRate()));
    }


    public DiabetesService() throws Exception {
        initModel();
    }

    //预测
    public boolean predict(int Pregnancies, int Glucose, int BloodPressure, int SkinThickness, int Insulin, double BMI, double DiabetesPedigreeFunction, int Age) throws Exception {
        //创建空instance
        Instance instance = new DenseInstance(9);
        instance.setValue(0, Pregnancies);
        instance.setValue(1, Glucose);
        instance.setValue(2, BloodPressure);
        instance.setValue(3, SkinThickness);
        instance.setValue(4, Insulin);
        instance.setValue(5, BMI);
        instance.setValue(6, DiabetesPedigreeFunction);
        instance.setValue(7, Age);
        instance.setDataset(blankInstances);

        boolean res = !(Math.abs(classifier.classifyInstance(instance)) < 1e-6);
        logger.debug("预测结果" + res);
        return res;
    }
}
