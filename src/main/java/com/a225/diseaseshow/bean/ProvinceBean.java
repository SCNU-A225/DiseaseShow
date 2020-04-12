package com.a225.diseaseshow.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// 统计每个省份的总病历数，总治愈数和总死亡数
public class ProvinceBean implements Writable {

    private String province;
    private int sum;
    private int cured;
    private int dead;

    public ProvinceBean(){
        super();
    }

    public ProvinceBean(String province, int sum, int cured, int dead){
        super();
        this.province = province;
        this.sum = sum;
        this.cured = cured;
        this.dead = dead;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(province);
        dataOutput.writeInt(sum);
        dataOutput.writeInt(cured);
        dataOutput.writeInt(dead);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.province = dataInput.readUTF();
        this.sum = dataInput.readInt();
        this.cured = dataInput.readInt();
        this.dead = dataInput.readInt();
    }

    @Override
    public String toString() {
        return province + "\t" + sum + "\t" + cured + "\t" + dead;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

}
