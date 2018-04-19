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
    //测试结果：true 已完成 false 未完成
    private boolean stroopState;

    @Generated(hash = 1978155298)
    public PersonData(String idcard, String name, String birth, String gender,
            String job, String education, String stroopA, String stroopB,
            String stroopC, boolean stroopState) {
        this.idcard = idcard;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.job = job;
        this.education = education;
        this.stroopA = stroopA;
        this.stroopB = stroopB;
        this.stroopC = stroopC;
        this.stroopState = stroopState;
    }
    @Generated(hash = 212076876)
    public PersonData() {
    }
    public String getIdcard() {
        return this.idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirth() {
        return this.birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getEducation() {
        return this.education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getStroopA() {
        return this.stroopA;
    }
    public void setStroopA(String stroopA) {
        this.stroopA = stroopA;
    }
    public String getStroopB() {
        return this.stroopB;
    }
    public void setStroopB(String stroopB) {
        this.stroopB = stroopB;
    }
    public String getStroopC() {
        return this.stroopC;
    }
    public void setStroopC(String stroopC) {
        this.stroopC = stroopC;
    }
    public boolean getStroopState() {
        return this.stroopState;
    }
    public void setStroopState(boolean stroopState) {
        this.stroopState = stroopState;
    }

}
