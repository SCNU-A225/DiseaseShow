package com.a225.diseaseshow.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
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

        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setFile(new File("src\\main\\resources\\data\\diabetes.arff"));
        Instances dataSet = arffLoader.getDataSet();
        dataSet.setClass(dataSet.attribute(dataSet.numAttributes() - 1));

        classifier = new J48();
        classifier.buildClassifier(dataSet);

        for (int i = 0; i < dataSet.numAttributes(); i++) {
            attributesList.add(dataSet.attribute(i));
        }
        blankInstances = new Instances("data", attributesList, 0);
        blankInstances.setClassIndex(dataSet.numAttributes() - 1);

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

    public boolean predict(int Pregnancies, int Glucose, int BloodPressure, int SkinThickness, int Insulin, double BMI, double DiabetesPedigreeFunction, int Age) throws Exception {
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
