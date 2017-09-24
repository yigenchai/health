package com.olosom.health.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 患者信息集合
 */

public class Patient implements Parcelable{

    public final static String TAG = "Patient";

    private String name;

    private int age;

    private int weight;

    private int height;

    /**
     * 血压-收缩压
     */
    private int SP;

    /**
     * 血压-舒张压
     */
    private int DP;

    /**
     * 1 = 男
     * 0 = 女
     */
    private int gender;

    private long createTime;

    public Patient(){}

    public Patient(String name, int age, int height, int weight, int SP,
                   int DP, int gender, long createTime){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.SP = SP;
        this.DP = DP;
        this.gender = gender;
        this.createTime = createTime;
    }

    protected Patient(Parcel in) {
        name = in.readString();
        age = in.readInt();
        weight = in.readInt();
        height = in.readInt();
        SP = in.readInt();
        DP = in.readInt();
        gender = in.readInt();
        createTime = in.readLong();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getDP() {
        return DP;
    }

    public void setDP(int DP) {
        this.DP = DP;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(weight);
        dest.writeInt(height);
        dest.writeInt(SP);
        dest.writeInt(DP);
        dest.writeInt(gender);
        dest.writeLong(createTime);
    }
}
