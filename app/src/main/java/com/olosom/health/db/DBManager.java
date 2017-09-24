package com.olosom.health.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.olosom.health.app.App;
import com.olosom.health.entity.Diagnosis;
import com.olosom.health.entity.DiagnosisParams;
import com.olosom.health.entity.DiagnosisResult;
import com.olosom.health.entity.Patient;
import com.olosom.health.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class DBManager {

    private static DBManager sDBManager = new DBManager();

    private DBOpenHelper mDBHelper;


    private DBManager() {
        mDBHelper = DBOpenHelper.getInstance(App.getInstance().getApplicationContext());
    }

    static synchronized DBManager getInstance() {
        if (sDBManager == null) {
            sDBManager = new DBManager();
        }
        return sDBManager;
    }

    synchronized void closeDB() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
        sDBManager = null;
    }

    /**
     * 保存一条诊断纪录
     */
    synchronized void saveDiagnosis(Diagnosis diagnosis) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        Patient patient = diagnosis.getPatient();
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_NAME, patient.getName());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_AGE, patient.getAge());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_HEIGHT, patient.getHeight());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_WEIGHT, patient.getWeight());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_SP, patient.getSP());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_DP, patient.getDP());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_GENDER, patient.getGender());
        values.put(DiagnosisDao.COLUMN_NAME_PATIENT_CREATE_TIME, String.valueOf(patient.getCreateTime()));

        DiagnosisResult diagnosisResult = diagnosis.getDiagnosisResult();
        String resultNameArr = CommonUtils.transformStringArrayToString(diagnosisResult.getName());
        if (!TextUtils.isEmpty(resultNameArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_RESULT_NAME_ARR, resultNameArr);
        }
        String resultResultArr = CommonUtils.transformStringArrayToString(diagnosisResult.getResult());
        if (!TextUtils.isEmpty(resultResultArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_RESULT_RESULT_ARR, resultResultArr);
        }
        String resultResultexArr = CommonUtils.transformStringArrayToString(diagnosisResult.getResultex());
        if (!TextUtils.isEmpty(resultResultexArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_RESULT_RESULTEX_ARR, resultResultexArr);
        }

        DiagnosisParams diagnosisParams = diagnosis.getDiagnosisParams();
        String paramsNameArr = CommonUtils.transformStringArrayToString(diagnosisParams.getName());
        if (!TextUtils.isEmpty(paramsNameArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_NAME_ARR, paramsNameArr);
        }
        String paramsValueArr = CommonUtils.transformFloatArrayToString(diagnosisParams.getValue());
        if (!TextUtils.isEmpty(paramsValueArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_VALUE_ARR, paramsValueArr);
        }
        String paramsTvArr = CommonUtils.transformStringArrayToString(diagnosisParams.getTv());
        if (!TextUtils.isEmpty(paramsTvArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_TV_ARR, paramsTvArr);
        }
        String paramsSvArr = CommonUtils.transformStringArrayToString(diagnosisParams.getSv());
        if (!TextUtils.isEmpty(paramsSvArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_SV_ARR, paramsSvArr);
        }
        String paramsPromptArr = CommonUtils.transformStringArrayToString(diagnosisParams.getPrompt());
        if (!TextUtils.isEmpty(paramsPromptArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_PROMPT_ARR, paramsPromptArr);
        }
        String paramsGradeArr = CommonUtils.transformStringArrayToString(diagnosisParams.getGrade());
        if (!TextUtils.isEmpty(paramsGradeArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_GRADE_ARR, paramsGradeArr);
        }
        String paramsFullNameArr = CommonUtils.transformStringArrayToString(diagnosisParams.getFullname());
        if (!TextUtils.isEmpty(paramsFullNameArr)) {
            values.put(DiagnosisDao.COLUMN_NAME_PARAMS_FULL_NAME_ARR, paramsFullNameArr);
        }

        values.put(DiagnosisDao.COLUMN_NAME_CREATE_TIME, String.valueOf(diagnosis.getCreateTime()));

        if (db.isOpen()) {
            db.replace(DiagnosisDao.TABLE_NAME, null, values);
        }
    }

    /**
     * 获取某个用户的诊断纪录集合
     */
    synchronized Map<Long, Diagnosis> getDiagnosisMap(long patientCreateTime) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Map<Long, Diagnosis> diagnosisMap = new HashMap<>();
        if (db.isOpen()) {
            Cursor cursor = db.query(DiagnosisDao.TABLE_NAME, new String[]{},
                    DiagnosisDao.COLUMN_NAME_PATIENT_CREATE_TIME + " =?",
                    new String[]{String.valueOf(patientCreateTime)}, null, null, null);
            while (cursor.moveToNext()) {
                String patientName = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_NAME));
                int patientAge = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_AGE));
                int patientHeight = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_HEIGHT));
                int patientWeight = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_WEIGHT));
                int patientSP = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_SP));
                int patientDP = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_DP));
                int patientGender = cursor.getInt(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PATIENT_GENDER));

                String resultNameArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_RESULT_NAME_ARR));
                String resultResultArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_RESULT_RESULT_ARR));
                String resultResultexArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_RESULT_RESULTEX_ARR));
                String[] resultName = CommonUtils.transformStringToStringArray(resultNameArr);
                String[] resultResult = CommonUtils.transformStringToStringArray(resultResultArr);
                String[] resultResultex = CommonUtils.transformStringToStringArray(resultResultexArr);

                String paramsNameArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_NAME_ARR));
                String paramsValueArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_VALUE_ARR));
                String paramsTvArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_TV_ARR));
                String paramsSvArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_SV_ARR));
                String paramsPromptArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_PROMPT_ARR));
                String paramsGradeArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_GRADE_ARR));
                String paramsFullNameArr = cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_PARAMS_FULL_NAME_ARR));
                String[] paramsName = CommonUtils.transformStringToStringArray(paramsNameArr);
                float[] paramsValue = CommonUtils.transformStringToFloatArray(paramsValueArr);
                String[] paramsTv = CommonUtils.transformStringToStringArray(paramsTvArr);
                String[] paramsSv = CommonUtils.transformStringToStringArray(paramsSvArr);
                String[] paramsPrompt = CommonUtils.transformStringToStringArray(paramsPromptArr);
                String[] paramsGrade = CommonUtils.transformStringToStringArray(paramsGradeArr);
                String[] paramsFullName = CommonUtils.transformStringToStringArray(paramsFullNameArr);

                Long createTime = Long.valueOf(cursor.getString(cursor.getColumnIndex(DiagnosisDao.COLUMN_NAME_CREATE_TIME)));

                Patient patient = new Patient(patientName, patientAge, patientHeight, patientWeight,
                        patientSP, patientDP, patientGender, Long.valueOf(patientCreateTime));
                DiagnosisResult diagnosisResult = new DiagnosisResult(resultName, resultResult, resultResultex);
                DiagnosisParams diagnosisParams = new DiagnosisParams(paramsName, paramsValue, paramsTv, paramsSv, paramsPrompt, paramsGrade, paramsFullName);
                Diagnosis diagnosis = new Diagnosis(patient, diagnosisResult,
                        diagnosisParams, createTime);

                diagnosisMap.put(createTime, diagnosis);
            }
            cursor.close();
        }
        return diagnosisMap;
    }


    /**
     * 保存一个患者信息
     */
    synchronized void savePatient(Patient patient) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PatientDao.COLUMN_NAME_NAME, patient.getName());
        values.put(PatientDao.COLUMN_NAME_AGE, patient.getAge());
        values.put(PatientDao.COLUMN_NAME_HEIGHT, patient.getHeight());
        values.put(PatientDao.COLUMN_NAME_WEIGHT, patient.getWeight());
        values.put(PatientDao.COLUMN_NAME_SP, patient.getSP());
        values.put(PatientDao.COLUMN_NAME_DP, patient.getDP());
        values.put(PatientDao.COLUMN_NAME_GENDER, patient.getGender());
        values.put(PatientDao.COLUMN_NAME_CREATE_TIME, String.valueOf(patient.getCreateTime()));
        if (db.isOpen()) {
            db.replace(PatientDao.TABLE_NAME, null, values);
        }
    }

    /**
     * 获取患者列表
     */
    synchronized List<Patient> getPatientList() {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        List<Patient> patientList = new ArrayList<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + PatientDao.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(PatientDao.COLUMN_NAME_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_AGE));
                int height = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_HEIGHT));
                int weight = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_WEIGHT));
                int sp = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_SP));
                int dp = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_DP));
                int gender = cursor.getInt(cursor.getColumnIndex(PatientDao.COLUMN_NAME_GENDER));
                String createTime = cursor.getString(cursor.getColumnIndex(PatientDao.COLUMN_NAME_CREATE_TIME));
                Patient patient = new Patient(name, age, height, weight,
                        sp, dp, gender, Long.valueOf(createTime));
                patientList.add(patient);
            }
            cursor.close();
        }
        return patientList;
    }

}
