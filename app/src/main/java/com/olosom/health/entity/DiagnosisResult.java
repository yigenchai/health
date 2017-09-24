package com.olosom.health.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DiagnosisResult implements Parcelable {

    public static final String TAG = "DiagnosisResult";

    private String[] name;

    private String[] result;

    private String[] resultex;

    /**
     * @param name     结果名字
     * @param result   结果代码
     * @param resultex 结果解释
     */
    public DiagnosisResult(String[] name, String[] result, String[] resultex) {
        this.name = name;
        this.result = result;
        this.resultex = resultex;
    }

    public DiagnosisResult(String[] name) {
        this.name = name;
    }

    protected DiagnosisResult(Parcel in) {
        name = in.createStringArray();
        result = in.createStringArray();
        resultex = in.createStringArray();
    }

    public static final Creator<DiagnosisResult> CREATOR = new Creator<DiagnosisResult>() {
        @Override
        public DiagnosisResult createFromParcel(Parcel in) {
            return new DiagnosisResult(in);
        }

        @Override
        public DiagnosisResult[] newArray(int size) {
            return new DiagnosisResult[size];
        }
    };

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public String[] getResultex() {
        return resultex;
    }

    public void setResultex(String[] resultex) {
        this.resultex = resultex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(name);
        dest.writeStringArray(result);
        dest.writeStringArray(resultex);
    }

}
