package com.olosom.health.db;

import com.olosom.health.entity.Diagnosis;

import java.util.List;
import java.util.Map;

/**
 * 诊断结果数据库
 */

public class DiagnosisDao {

    final static String TABLE_NAME = "diagnosis_result";

    final static String COLUMN_NAME_PATIENT_NAME = "patient_name";
    final static String COLUMN_NAME_PATIENT_AGE = "patient_age";
    final static String COLUMN_NAME_PATIENT_HEIGHT = "patient_height";
    final static String COLUMN_NAME_PATIENT_WEIGHT = "patient_weight";
    final static String COLUMN_NAME_PATIENT_SP = "patient_sp";
    final static String COLUMN_NAME_PATIENT_DP = "patient_dp";
    final static String COLUMN_NAME_PATIENT_GENDER = "patient_gender";
    final static String COLUMN_NAME_PATIENT_CREATE_TIME = "patient_create_time";

    final static String COLUMN_NAME_RESULT_NAME_ARR = "result_name_arr";
    final static String COLUMN_NAME_RESULT_RESULT_ARR = "result_result_arr";
    final static String COLUMN_NAME_RESULT_RESULTEX_ARR = "result_resultex_arr";


    final static String COLUMN_NAME_PARAMS_NAME_ARR = "params_name_arr";
    final static String COLUMN_NAME_PARAMS_VALUE_ARR = "params_value_arr";
    final static String COLUMN_NAME_PARAMS_TV_ARR = "params_tv_arr";
    final static String COLUMN_NAME_PARAMS_SV_ARR = "params_sv_arr";
    final static String COLUMN_NAME_PARAMS_PROMPT_ARR = "params_prompt_arr";
    final static String COLUMN_NAME_PARAMS_GRADE_ARR = "params_grade_arr";
    final static String COLUMN_NAME_PARAMS_FULL_NAME_ARR = "params_full_name_arr";

    final static String COLUMN_NAME_CREATE_TIME = "create_time";

    /**
     * 保存一条诊断纪录
     */
    public void saveDiagnosis(Diagnosis diagnosis){
        DBManager.getInstance().saveDiagnosis(diagnosis);
    }

    /**
     * 获取某个用户的诊断纪录集合
     */
    public Map<Long, Diagnosis> getDiagnosisMap(long patientCreateTime){
        return DBManager.getInstance().getDiagnosisMap(patientCreateTime);
    }


    public void closeDB() {
        DBManager.getInstance().closeDB();
    }
}
