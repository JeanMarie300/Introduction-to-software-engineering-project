package com.example.siaedgard.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERTYPE = "usertype";
    public static final String COLUMN_POSTALCODE = "postal_code";

    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_IDSERVICES = "_id";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_SERVICERATE = "servicerate";

    public static final  String  TABLE_1 = "CREATE TABLE " +
    TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME
                + " TEXT," + COLUMN_LASTNAME + " TEXT," +COLUMN_USERNAME +
            " TEXT," +COLUMN_PASSWORD + " TEXT," + COLUMN_USERTYPE + " TEXT," +
    COLUMN_POSTALCODE + " TEXT" + ")";

    public static  String TABLE_2 = "CREATE TABLE " +
    TABLE_SERVICES + "("
            + COLUMN_IDSERVICES + " INTEGER PRIMARY KEY," + COLUMN_SERVICENAME
                + " TEXT," + COLUMN_SERVICERATE + " TEXT" + ")";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_1);
        db.execSQL(TABLE_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    //Insert a new service in the database

    public void addServices (Services services){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME, services.getName());
        values.put(COLUMN_SERVICERATE,services.gethourRate());
        db.insert(TABLE_SERVICES,null,values);
        db.close();
    }

    //Insert a new user in the database

    public void addUsers (User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME,user.getLastName());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_USERTYPE, user.getUserType());
        values.put(COLUMN_POSTALCODE, user.getUserType());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    //Fetch all the services from the database


    public Map findServices(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+ TABLE_SERVICES;
        Cursor cursor = db.rawQuery(query,null);
        Map<String, String> tableInfo = new HashMap<String, String>();
        while(cursor.moveToNext()){
            tableInfo.put(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICENAME)), cursor.getString(cursor.getColumnIndex(COLUMN_SERVICERATE)));
        }
        return tableInfo;
    }

    public void deleteServices (String serviceName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+ TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, COLUMN_IDSERVICES + " = " + idStr, null);
            cursor.close();
        }
        db.close();
    }



    public void updateServices (String oldServiceName,String serviceName, String serviceRate ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(COLUMN_SERVICENAME, serviceName);
        newValues.put(COLUMN_SERVICERATE, serviceRate);
        db.update(TABLE_SERVICES, newValues, COLUMN_SERVICENAME + " = ?" , new String[]{oldServiceName});
    }

    public boolean deleteUsers (String serviceName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+ TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + "=" + serviceName + "\"";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}