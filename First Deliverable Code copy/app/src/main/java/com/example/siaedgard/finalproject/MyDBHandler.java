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
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_USERTYPE = "usertype";
    public static final String COLUMN_POSTALCODE = "postal_code";

    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_IDSERVICES = "_id";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_SERVICERATE = "servicerate";

    public static final String TABLE_SERVICE_PROVIDER_INFO = "service_provider";
    public static final String COLUMN_FORMID = "_id";
    public static final String COLUMN_COMPANYNAME = "companyname";
    public static final String COLUMN_LICENSE = "license";
    public static final String COLUMN_EXPERTISE = "expertise";
    public static final String COLUMN_EXPERIENCEYEARS = "experienceyears";
    public static final String COLUMN_PHONENUMBER = "phonenumber";
    public static final String COLUMN_USERID = "userid";

    public static final String TABLE_SERVICE_PROVIDER_SERVICES = "service_provider_services";
    public static final String COLUMN_IDTABLE = "_id";
    public static final String COLUMN_SERVICEID = "service";
    public static final String USERID = "user_id";

    public static final String TABLE_SERVICE_PROVIDER_AVAILABILITIES = "service_provider_availabilities";
    public static final String AVAILABILITIES_ID = "_id";
    public static final String COLUMN_INITIAL_DATE = "date";
    public static final String COLUMN_INITIAL_TIME = "initial_time";
    public static final String FINAL_TIME = "final_time";
    public static final String SERVICEPROVIDERD = "userID";

    public static final  String  TABLE_1 = "CREATE TABLE " +
            TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME
            + " TEXT," + COLUMN_LASTNAME + " TEXT," +COLUMN_BIRTHDAY +
            " TEXT," +COLUMN_POSTALCODE + " TEXT," + COLUMN_USERTYPE + " TEXT," +
            COLUMN_USERNAME + " TEXT,"  +
            COLUMN_PASSWORD + " TEXT" + ")";

    public static  String TABLE_2 = "CREATE TABLE " +
            TABLE_SERVICES + "("
            + COLUMN_IDSERVICES + " INTEGER PRIMARY KEY," + COLUMN_SERVICENAME
            + " TEXT," + COLUMN_SERVICERATE + " TEXT" + ")";

    public static final  String  TABLE_3 = "CREATE TABLE " +
            TABLE_SERVICE_PROVIDER_INFO + "("
            + COLUMN_FORMID + " INTEGER PRIMARY KEY," + COLUMN_COMPANYNAME
            + " TEXT," + COLUMN_LICENSE + " TEXT,"+ COLUMN_EXPERTISE + " TEXT,"
            + COLUMN_EXPERIENCEYEARS +
            " TEXT,"+ COLUMN_PHONENUMBER +
            " TEXT," +COLUMN_USERID + " TEXT," + " FOREIGN KEY ("+COLUMN_USERID+") " +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";


    public static final  String  TABLE_4 = "CREATE TABLE " +
            TABLE_SERVICE_PROVIDER_SERVICES + "("
            + COLUMN_IDTABLE + " INTEGER PRIMARY KEY," +
            COLUMN_SERVICEID + " TEXT," +
            USERID +  " TEXT," + " FOREIGN KEY ("+COLUMN_SERVICEID+")" +
            " REFERENCES "+ TABLE_SERVICES+ " ("+COLUMN_IDSERVICES+"), " + " FOREIGN KEY ("+USERID+")" +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";

    public static final  String  TABLE_5 = "CREATE TABLE " +
            TABLE_SERVICE_PROVIDER_AVAILABILITIES + "("
            + AVAILABILITIES_ID + " INTEGER PRIMARY KEY," + COLUMN_INITIAL_DATE
            + " TEXT," + COLUMN_INITIAL_TIME + " TEXT,"+ FINAL_TIME +
            " TEXT," + SERVICEPROVIDERD + " TEXT," + " FOREIGN KEY ("+SERVICEPROVIDERD+") " +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_1);
        db.execSQL(TABLE_2);
        db.execSQL(TABLE_3);
        db.execSQL(TABLE_4);
        db.execSQL(TABLE_5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_AVAILABILITIES);
        onCreate(db);
    }

    public void addAvailabilities (HashMap<String, String> dateInfo, int serviceProviderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INITIAL_DATE, dateInfo.get("initDate"));
        values.put(COLUMN_INITIAL_TIME,dateInfo.get("finalDate"));
        values.put(FINAL_TIME,dateInfo.get("finalTime"));
        values.put(SERVICEPROVIDERD,serviceProviderId);
        db.insert(TABLE_SERVICE_PROVIDER_AVAILABILITIES,null,values);
        db.close();
    }

    public User findUser(String username) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public void addServices (Services services){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME, services.getName());
        values.put(COLUMN_SERVICERATE,services.gethourRate());
        db.insert(TABLE_SERVICES,null,values);
        db.close();
    }


    public long addUsers (User user){
        SQLiteDatabase db = this.getWritableDatabase();
        User tempUser = findUser(user.getUsername());
        if (tempUser != null) {
            return -2;
        } else {
            SQLiteDatabase db2 = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_FIRSTNAME, user.getFirstName());
            values.put(COLUMN_LASTNAME,user.getLastName());
            values.put(COLUMN_USERNAME, user.getUsername());
            values.put(COLUMN_PASSWORD,user.getPassword());
            values.put(COLUMN_BIRTHDAY,user.getBirthday());
            values.put(COLUMN_USERTYPE, user.getUserType());
            values.put(COLUMN_POSTALCODE, user.getPostalCode());
            long id = db2.insert(TABLE_USERS,null,values);
            db2.close();
            return id;
        }
    }

    public void ServiceProviderInfo (ServiceProvider serviceProvider, String serviceProviderId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANYNAME, serviceProvider.getCompanyName());
        values.put(COLUMN_EXPERIENCEYEARS,serviceProvider.getexperienceYears());
        values.put(COLUMN_LICENSE, serviceProvider.getLicenseName());
        values.put(COLUMN_PHONENUMBER,serviceProvider.getPhoneNumber());
        values.put(COLUMN_USERID,serviceProviderId);
        db.insert(TABLE_SERVICE_PROVIDER_INFO,null,values);
        db.close();
    }


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