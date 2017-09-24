package com.olosom.health.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 诊断信息集合
 */

public class Diagnosis implements Parcelable, Comparable<Diagnosis> {

    public static final String TAG = "Diagnosis";

    private Patient patient;

    private long createTime;

    private DiagnosisParams diagnosisParams;

    private DiagnosisResult diagnosisResult;

    public Diagnosis() {
        createTime = System.currentTimeMillis();
        patient = new Patient("测试", 25, 160, 50, 110, 90, 1, createTime);
        diagnosisParams = new DiagnosisParams(new String[]{
                "iCF_", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iVC_", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iBC_", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iMF_", "iPR收拾收拾事实上", "iPR收拾收拾事实上", "iPR收拾收拾事实上",
                "iEND"
        });
        diagnosisResult = new DiagnosisResult(new String[]{
                "心脏功能", "血管状况", "血液状态",
                "微循环功能", "肺功能", "身材状况", "综合意见"
        });
    }

    public Diagnosis(Patient patient, DiagnosisResult diagnosisResult, DiagnosisParams diagnosisParams, long createTime) {
        this.patient = patient;
        this.diagnosisResult = diagnosisResult;
        this.diagnosisParams = diagnosisParams;
        this.createTime = createTime;
    }

    protected Diagnosis(Parcel in) {
        patient = in.readParcelable(Patient.class.getClassLoader());
        createTime = in.readLong();
        diagnosisParams = in.readParcelable(DiagnosisParams.class.getClassLoader());
        diagnosisResult = in.readParcelable(DiagnosisResult.class.getClassLoader());
    }

    public static final Creator<Diagnosis> CREATOR = new Creator<Diagnosis>() {
        @Override
        public Diagnosis createFromParcel(Parcel in) {
            return new Diagnosis(in);
        }

        @Override
        public Diagnosis[] newArray(int size) {
            return new Diagnosis[size];
        }
    };

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public DiagnosisParams getDiagnosisParams() {
        return diagnosisParams;
    }

    public void setDiagnosisParams(DiagnosisParams diagnosisParams) {
        this.diagnosisParams = diagnosisParams;
    }

    public DiagnosisResult getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(DiagnosisResult diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(patient, flags);
        dest.writeLong(createTime);
        dest.writeParcelable(diagnosisParams, flags);
        dest.writeParcelable(diagnosisResult, flags);
    }

    @Override
    public int compareTo(Diagnosis another) {
        long lhs = this.getCreateTime();
        long rhs = another.getCreateTime();
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }
}
