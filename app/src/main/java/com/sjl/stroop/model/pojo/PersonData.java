package com.sjl.stroop.model.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * PersonData
 *
 * @author 林zero
 * @date 2018/4/18
 */
@Entity
public class PersonData {

    //身份证
    private String idcard;
    //姓名
    private String name;
    //生日
    private String birth;
    //性别
    private String gender;
    //职业
    private String job;
    //学历
    private String education;
    //A组测试数据
    private String stroopA;
    //B组测试数据
    private String stroopB;
    //C组测试数据
    private String stroopC;

    @Generated(hash = 1442824864)
    public PersonData(String idcard, String name, String birth, String gender,
            String job, String education, String stroopA, String stroopB,
            String stroopC) {
        this.idcard = idcard;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.job = job;
        this.education = education;
        this.stroopA = stroopA;
        this.stroopB = stroopB;
        this.stroopC = stroopC;
    }

    @Generated(hash = 212076876)
    public PersonData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getStroopA() {
        return stroopA;
    }

    public void setStroopA(String stroopA) {
        this.stroopA = stroopA;
    }

    public String getStroopB() {
        return stroopB;
    }

    public void setStroopB(String stroopB) {
        this.stroopB = stroopB;
    }

    public String getStroopC() {
        return stroopC;
    }

    public void setStroopC(String stroopC) {
        this.stroopC = stroopC;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
