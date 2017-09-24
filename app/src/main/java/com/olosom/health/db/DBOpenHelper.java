package com.olosom.health.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 病人数据库
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBOpenHelper";

    private static final String DB_NAME = "health.db";

    /**
     * 数据库版本
     **/
    private static int DB_VERSION = 1;

    private static DBOpenHelper sInstance;

    private static final String PATIENT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + PatientDao.TABLE_NAME + " ("
            + PatientDao.COLUMN_NAME_NAME + " TEXT, "
            + PatientDao.COLUMN_NAME_AGE + " INTEGER, "
            + PatientDao.COLUMN_NAME_HEIGHT + " INTEGER, "
            + PatientDao.COLUMN_NAME_WEIGHT + " INTEGER, "
            + PatientDao.COLUMN_NAME_SP + " INTEGER, "
            + PatientDao.COLUMN_NAME_DP + " INTEGER, "
            + PatientDao.COLUMN_NAME_GENDER + " INTEGER, "
            + PatientDao.COLUMN_NAME_CREATE_TIME + " TEXT PRIMARY KEY);";

    private static final String DIAGNOSIS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + DiagnosisDao.TABLE_NAME + " ("

            + DiagnosisDao.COLUMN_NAME_PATIENT_NAME + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_AGE + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_HEIGHT + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_WEIGHT + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_SP + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_DP + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_GENDER + " INTEGER, "
            + DiagnosisDao.COLUMN_NAME_PATIENT_CREATE_TIME + " TEXT, "

            + DiagnosisDao.COLUMN_NAME_RESULT_NAME_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_RESULT_RESULT_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_RESULT_RESULTEX_ARR + " TEXT, "

            + DiagnosisDao.COLUMN_NAME_PARAMS_NAME_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_VALUE_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_TV_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_SV_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_PROMPT_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_GRADE_ARR + " TEXT, "
            + DiagnosisDao.COLUMN_NAME_PARAMS_FULL_NAME_ARR + " TEXT, "

            + DiagnosisDao.COLUMN_NAME_CREATE_TIME + " TEXT PRIMARY KEY);";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(PATIENT_TABLE_CREATE);
            db.execSQL(DIAGNOSIS_TABLE_CREATE);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
