package com.zpstal.zpstaltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "Databases";

    // PERSONAL TABLE.
    private static final String TABLE_NAME = "personal";
    private static final String ID = "id";
    private static final String ID_PERSON = "id_person";
    private static final String FIO = "fio";
    private static final String DATE_BIRTH = "date_birth";
    private static final String GENDER = "gender";
    private static final String ID_DEPARTMENT = "id_department";
    private static final String ID_POSITION = "id_position";

    // DEPARTMENT TABLE, INNER TABLE.
    private static final String TABLE_NAME_DEPARTMENT = "department";
    private static final String ID_DEP = "id_dep";
    private static final String ID_DEPARTMENTS = "id_departments";
    private static final String DESCRIBE_DEPARTMENTS = "describe_departments";
    private static final String NOTE_DEPARTMENTS = "note_departments";

    // POSITION TABLE, INNER TABLE.
    private static final String TABLE_NAME_POSITION = "position";
    private static final String ID_POS = "id_pos";
    private static final String POSITION_ID = "position_id";
    private static final String POSITION_NAME = "position_name";
    private static final String POSITION_SALERY = "position_salery";
    private static final String POSITION_DESCRIBE = "position_describe";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // CREATE PERSONAL TABLE.
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ID_PERSON + " INTEGER," + FIO + " TEXT," + DATE_BIRTH + " TEXT," + GENDER + " COLLATE NOCASE," +
                " TEXT," + ID_DEPARTMENT + " INTEGER," + ID_POSITION +  " INTEGER" + " )";

        // CREATE DEPARTMENT TABLE.
        String createDepartment = "CREATE TABLE " + TABLE_NAME_DEPARTMENT + "(" +
                ID_DEP + " INTEGER PRIMARY KEY AUTOINCREMENT," + ID_DEPARTMENTS + " INTEGER," +
                DESCRIBE_DEPARTMENTS + " TEXT," + NOTE_DEPARTMENTS + " TEXT" + ")";

        // CREATE POSITION TABLE.
        String createPosition = "CREATE TABLE " + TABLE_NAME_POSITION + "(" +
                ID_POS + " INTEGER PRIMARY KEY AUTOINCREMENT," + POSITION_ID + " INTEGER," +
                POSITION_NAME + " TEXT," + POSITION_SALERY + " INTEGER," +
                POSITION_DESCRIBE + " TEXT" + ")";

        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(createDepartment);
        sqLiteDatabase.execSQL(createPosition);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DEPARTMENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_POSITION);
        onCreate(sqLiteDatabase);

    }

    // CREATE PERSONAL TABLE
    public boolean addWorker(int id_pers, String fio, String dataBirth, String gend, int idDep, int idPos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PERSON, id_pers);
        contentValues.put(FIO, fio);
        contentValues.put(DATE_BIRTH, dataBirth);
        contentValues.put(GENDER, gend);
        contentValues.put(ID_DEPARTMENT, idDep);
        contentValues.put(ID_POSITION, idPos);
        Log.d(TAG, "ADDED_DATA " +  " to_table" + TABLE_NAME);

        long result_name = db.insert(TABLE_NAME, null, contentValues);
        if (result_name == -1) {
            return false;
        } else {
            return true;
        }
    }

    // CREATE DEPARTMENT TABLE
    public boolean addDepartment(int id_dep, String name, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_DEPARTMENTS, id_dep);
        contentValues.put(DESCRIBE_DEPARTMENTS, name);
        contentValues.put(NOTE_DEPARTMENTS, note);
        Log.d(TAG, "ADDED_DATA " +  " to_table" + TABLE_NAME_DEPARTMENT);

        long result_name = db.insert(TABLE_NAME_DEPARTMENT, null, contentValues);
        if (result_name == -1) {
            return false;
        } else {
            return true;
        }
    }

    // CREATE POSITION TABLE
    public boolean addPosition(int id_pos, String name, int sal, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(POSITION_ID, id_pos);
        contentValues.put(POSITION_NAME, name);
        contentValues.put(POSITION_SALERY, sal);
        contentValues.put(POSITION_DESCRIBE, note);
        Log.d(TAG, "ADDED_DATA " +  " to_table" + TABLE_NAME_POSITION);

        long result_name = db.insert(TABLE_NAME_POSITION, null, contentValues);
        if (result_name == -1) {
            return false;
        } else {
            return true;
        }
    }

    // SELECT FROM MAIN TABLE ON MAIN PAGE
    public List<DataPersonal> personalData() {
        List<DataPersonal> dataList = new ArrayList<>();
        String STICKER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STICKER_DETAIL_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    DataPersonal data = new DataPersonal();
                    data.id_person = cursor.getString(cursor.getColumnIndex(ID_PERSON));
                    data.fio = cursor.getString(cursor.getColumnIndex(FIO));
                    data.birth = cursor.getString(cursor.getColumnIndex(DATE_BIRTH));
                    data.gender = cursor.getString(cursor.getColumnIndex(GENDER));
                    data.idDep = cursor.getString(cursor.getColumnIndex(ID_DEPARTMENT));
                    data.idPos = cursor.getString(cursor.getColumnIndex(ID_POSITION));
                    dataList.add(data);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dataList;
    }


    // SELECT FROM DEPARTMENT TABLE.
    public List<DataDepartment> departmentData(String id_dep) {
        List<DataDepartment> dataList = new ArrayList<>();
        String STICKER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NAME_DEPARTMENT + " WHERE " +
                ID_DEPARTMENTS + " =?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STICKER_DETAIL_SELECT_QUERY, new String[] {id_dep});
        try {
            if (cursor.moveToFirst()) {
                do {
                    DataDepartment data = new DataDepartment();
                    data.id = cursor.getString(cursor.getColumnIndex(ID_DEPARTMENTS));
                    data.name = cursor.getString(cursor.getColumnIndex(DESCRIBE_DEPARTMENTS));
                    data.note = cursor.getString(cursor.getColumnIndex(NOTE_DEPARTMENTS));
                    dataList.add(data);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dataList;
    }

    // SELECT FROM POSITION TABLE.
    public List<DataPosition> positionData(String id_pos) {
        List<DataPosition> dataList = new ArrayList<>();
        String STICKER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NAME_POSITION + " WHERE " +
                POSITION_ID + " =?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STICKER_DETAIL_SELECT_QUERY, new String[] {id_pos});
        try {
            if (cursor.moveToFirst()) {
                do {
                    DataPosition data = new DataPosition();
                    data.id = cursor.getString(cursor.getColumnIndex(POSITION_ID));
                    data.name = cursor.getString(cursor.getColumnIndex(POSITION_NAME));
                    data.salary = cursor.getString(cursor.getColumnIndex(POSITION_SALERY));
                    data.note = cursor.getString(cursor.getColumnIndex(POSITION_DESCRIBE));
                    dataList.add(data);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dataList;
    }

    // SORT MAIN TABLE BY MALE
    public List<DataPersonal> sortMaleData(String gender) {
        List<DataPersonal> dataList = new ArrayList<>();
        String STICKER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + GENDER +
                " COLLATE NOCASE" + " =?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STICKER_DETAIL_SELECT_QUERY, new String[]{gender});
        try {
            if (cursor.moveToFirst()) {
                do {
                    DataPersonal data = new DataPersonal();
                    data.id_person = cursor.getString(cursor.getColumnIndex(ID_PERSON));
                    data.fio = cursor.getString(cursor.getColumnIndex(FIO));
                    data.birth = cursor.getString(cursor.getColumnIndex(DATE_BIRTH));
                    data.gender = cursor.getString(cursor.getColumnIndex(GENDER));
                    data.idDep = cursor.getString(cursor.getColumnIndex(ID_DEPARTMENT));
                    data.idPos = cursor.getString(cursor.getColumnIndex(ID_POSITION));
                    dataList.add(data);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return dataList;
    }



    // IF ID RECORD EXIST IN PERSON.
    public boolean existOrNot (String id_pers) {
        SQLiteDatabase db = getReadableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_PERSON + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[] {id_pers});

        if (cursor.getCount() > 0) return true;
        else return false;
    }

    // IF ID RECORD EXIST IN DEPARTAMENT.
    public boolean existOrNotIdDepartment (String id_department) {
        SQLiteDatabase db = getReadableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME_DEPARTMENT + " WHERE " + ID_DEP + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[] {id_department});

        if (cursor.getCount() > 0) return true;
        else return false;
    }

    // IF ID RECORD EXIST IN POSITION.
    public boolean existOrNotIdPosition (String id_pos) {
        SQLiteDatabase db = getReadableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME_POSITION + " WHERE " + POSITION_ID + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[] {id_pos});

        if (cursor.getCount() > 0) return true;
        else return false;
    }

    // DELETE RECORD FROM BASE.
    public boolean deleteItem(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ID_PERSON + " = " + id);
        db.close();
        return true;
    }

}