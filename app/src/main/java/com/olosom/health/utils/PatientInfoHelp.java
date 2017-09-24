package com.olosom.health.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.olosom.health.entity.Patient;


/**
 * 用户信息工具类
 */

public class PatientInfoHelp {

    /**
     * 初始化当前用户信息
     */
    public static Patient initCurrentPatient(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME_PATIENT, Context.MODE_PRIVATE);
        Patient patient = new Patient();
        patient.setCreateTime(sp.getLong(Constants.SP_KEY_PATIENT_CREATE_TIME, 1));
        patient.setName(sp.getString(Constants.SP_KEY_PATIENT_NAME, "异常"));
        patient.setAge(sp.getInt(Constants.SP_KEY_PATIENT_AGE, 1));
        patient.setWeight(sp.getInt(Constants.SP_KEY_PATIENT_WEIGHT, 1));
        patient.setHeight(sp.getInt(Constants.SP_KEY_PATIENT_HEIGHT, 1));
        patient.setSP(sp.getInt(Constants.SP_KEY_PATIENT_SP, 1));
        patient.setDP(sp.getInt(Constants.SP_KEY_PATIENT_DP, 1));
        patient.setGender(sp.getInt(Constants.SP_KEY_PATIENT_GENDER, 1));
        return patient;
    }

    /**
     * 清除 SP 中保存的用户信息
     */
    public static void cleanPatientInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME_PATIENT, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }

    /**
     * 保存当前用户信息
     */
    public static void savePatientInfo(Context context, Patient patient) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME_PATIENT, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putLong(Constants.SP_KEY_PATIENT_CREATE_TIME, patient.getCreateTime());
        checkValueAndSave(edit, Constants.SP_KEY_PATIENT_NAME, patient.getName());
        edit.putInt(Constants.SP_KEY_PATIENT_AGE, patient.getAge());
        edit.putInt(Constants.SP_KEY_PATIENT_WEIGHT, patient.getWeight());
        edit.putInt(Constants.SP_KEY_PATIENT_HEIGHT, patient.getHeight());
        edit.putInt(Constants.SP_KEY_PATIENT_SP, patient.getSP());
        edit.putInt(Constants.SP_KEY_PATIENT_DP, patient.getDP());
        edit.putInt(Constants.SP_KEY_PATIENT_GENDER, patient.getGender());
        edit.apply();
    }

    /**
     * 通过 SP 保存信息
     */
    public static void saveNormalInfo(Context context,
                                      String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME_PATIENT, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        checkValueAndSave(edit, key, value);
        edit.apply();
    }


    /**
     * 检查所存信息是否为空，不为空则保存
     */
    private static void checkValueAndSave(Editor edit, String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            edit.putString(key, value);
        }
    }


}
