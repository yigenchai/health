package com.olosom.health.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DiagnosisParams implements Parcelable {

    public static final String TAG = "DiagnosisParams";

    /**
     * 心脏功能
     */
    public static final int TYPE_HEART = 0;

    /**
     * 血管功能
     */
    public static final int TYPE_BLOOD_VESSEL = 1;

    /**
     * 血液功能
     */
    public static final int TYPE_BLOOD = 2;

    /**
     * 微循环功能
     */
    public static final int TYPE_MICROCIRCULATION = 3;

    /**
     * 各种类型的参数在数据集中的开始位置和长度
     */
    public static final int TYPE_HEART_START_POSITION = 1;
    public static final int TYPE_HEART_LENGTH = 15;

    public static final int TYPE_BLOOD_VESSEL_START_POSITION = 17;
    public static final int TYPE_BLOOD_VESSEL_LENGTH = 14;

    public static final int TYPE_BLOOD_START_POSITION = 32;
    public static final int TYPE_BLOOD_LENGTH = 3;
    public static final int TYPE_MICROCIRCULATION_START_POSITION = 36;
    public static final int TYPE_MICROCIRCULATION_LENGTH = 3;

    private String[] name;
    private float[] value;
    private String[] tv;
    private String[] sv;
    private String[] prompt;
    private String[] grade;
    private String[] unit;
    private String[] tips;
    private String[] fullname;

    /**
     * @param name     参数名字
     * @param value    实际测量值
     * @param tv       实际测量值字符串
     * @param sv       标准值范围字符串
     * @param prompt   参数分析后提示，”-“; “--"; “+”; “++”
     * @param grade    参数重要性等级，值越大临床意义越大
     * @param unit     参数单位
     * @param tips     参数解释
     * @param fullname 参数全名
     */
    public DiagnosisParams(String[] name, float[] value, String[] tv, String[] sv, String[] prompt, String[] grade, String[] unit, String[] tips, String[] fullname) {
        this.name = name;
        this.value = value;
        this.tv = tv;
        this.sv = sv;
        this.prompt = prompt;
        this.grade = grade;
        this.unit = unit;
        this.tips = tips;
        this.fullname = fullname;
    }

    public DiagnosisParams(String[] name, float[] value, String[] tv, String[] sv, String[] prompt, String[] grade, String[] fullname) {
        this.name = name;
        this.value = value;
        this.tv = tv;
        this.sv = sv;
        this.prompt = prompt;
        this.grade = grade;
        this.fullname = fullname;
    }

    public DiagnosisParams(String[] fullname) {
        this.fullname = fullname;
    }

    protected DiagnosisParams(Parcel in) {
        name = in.createStringArray();
        value = in.createFloatArray();
        tv = in.createStringArray();
        sv = in.createStringArray();
        prompt = in.createStringArray();
        grade = in.createStringArray();
        unit = in.createStringArray();
        tips = in.createStringArray();
        fullname = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(name);
        dest.writeFloatArray(value);
        dest.writeStringArray(tv);
        dest.writeStringArray(sv);
        dest.writeStringArray(prompt);
        dest.writeStringArray(grade);
        dest.writeStringArray(unit);
        dest.writeStringArray(tips);
        dest.writeStringArray(fullname);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiagnosisParams> CREATOR = new Creator<DiagnosisParams>() {
        @Override
        public DiagnosisParams createFromParcel(Parcel in) {
            return new DiagnosisParams(in);
        }

        @Override
        public DiagnosisParams[] newArray(int size) {
            return new DiagnosisParams[size];
        }
    };

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        this.value = value;
    }

    public String[] getTv() {
        return tv;
    }

    public void setTv(String[] tv) {
        this.tv = tv;
    }

    public String[] getSv() {
        return sv;
    }

    public void setSv(String[] sv) {
        this.sv = sv;
    }

    public String[] getPrompt() {
        return prompt;
    }

    public void setPrompt(String[] prompt) {
        this.prompt = prompt;
    }

    public String[] getGrade() {
        return grade;
    }

    public void setGrade(String[] grade) {
        this.grade = grade;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getTips() {
        return tips;
    }

    public void setTips(String[] tips) {
        this.tips = tips;
    }

    public String[] getFullname() {
        return fullname;
    }

    public void setFullname(String[] fullname) {
        this.fullname = fullname;
    }

}
