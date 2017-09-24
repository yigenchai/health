package com.olosom.health.db;


import com.olosom.health.entity.Patient;

import java.util.List;

/**
 * 患者数据库操作类
 */

public class PatientDao {


    final static String TABLE_NAME = "patient";

    final static String COLUMN_NAME_NAME = "name";

    final static String COLUMN_NAME_AGE = "age";

    final static String COLUMN_NAME_HEIGHT = "height";

    final static String COLUMN_NAME_WEIGHT = "weight";

    final static String COLUMN_NAME_SP = "sp";

    final static String COLUMN_NAME_DP = "dp";

    final static String COLUMN_NAME_GENDER = "gender";

    final static String COLUMN_NAME_CREATE_TIME = "create_time";

    /**
     * 保存一个患者信息
     */
    public void savePatient(Patient patient) {
        DBManager.getInstance().savePatient(patient);
    }

    /**
     * 获取患者列表
     */
    public List<Patient> getPatientList() {
        return DBManager.getInstance().getPatientList();
    }


    public void closeDB() {
        DBManager.getInstance().closeDB();
    }
}
