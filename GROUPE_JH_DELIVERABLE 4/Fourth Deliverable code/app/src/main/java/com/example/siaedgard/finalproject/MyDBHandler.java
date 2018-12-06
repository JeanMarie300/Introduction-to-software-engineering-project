package com.example.siaedgard.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_USERTYPE = "usertype";
    public static final String COLUMN_ADDRESS = "address";
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
    public static final String COLUMN_SERVICENAMEPROVIDERTABLE = "service_name";
    public static final String COLUMN_SERVICEPRICEPROVIDERTABLE = "service_price";
    public static final String USERID = "user_id";

    public static final String TABLE_SERVICE_PROVIDER_AVAILABILITIES = "service_provider_availabilities";
    public static final String AVAILABILITIES_ID = "_id";
    public static final String COLUMN_INITIAL_DATE = "date";
    public static final String COLUMN_INITIAL_TIME = "initial_time";
    public static final String FINAL_TIME = "final_time";
    public static final String SERVICEPROVIDERD = "userID";

    public static final String TABLE_BOOKING = "bookings";
    public static final String BOOKING_ID = "_id";
    public static final String HOME_OWNER_ID = "home_owner_id";
    public static final String SERVICE_PROVIDER_ID = "service_provider_id";
    public static final String BOOKING_RATING = "booking_rating";
    public static final String BOOKING_TIME = "booking_time";


    public static final String TABLE_FEEDBACK = "feedback";
    public static final String RATING_ID = "_id";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_RATING = "ratings";
    public static final String HOMEWONERIDBOOKING = "home_owner";
    public static final String SERVICEPROVIDER_IDBOOKING = "service_provider";
    public static final String FEEDBACK_BOOKING_ID = "booking_id";

    public static final String TABLE_SERVICE_PROVIDER_RATING = "service_provider_rating";
    public static final String _ID = "_id";
    public static final String SERVICE_PROVIDER_RATED = "service_provider_id";
    public static final String RATING_TO_SERVICE_PROVIDER = "rating";





    public static final  String  TABLE_1 = "CREATE TABLE " +
            TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME
            + " TEXT,"  + COLUMN_BIRTHDAY +
            " TEXT," +COLUMN_POSTALCODE + " TEXT," + COLUMN_USERTYPE + " TEXT," +
            COLUMN_USERNAME + " TEXT,"  +COLUMN_ADDRESS + " TEXT,"+
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
            COLUMN_SERVICENAMEPROVIDERTABLE + " TEXT,"+ COLUMN_SERVICEPRICEPROVIDERTABLE + " TEXT," +
            USERID +  " TEXT," + " FOREIGN KEY ("+COLUMN_SERVICENAMEPROVIDERTABLE+")" +
            " REFERENCES "+ TABLE_SERVICES+ " ("+COLUMN_SERVICENAME+"), " +" FOREIGN KEY ("+COLUMN_SERVICEPRICEPROVIDERTABLE+")" +
            " REFERENCES "+ TABLE_SERVICES+ " ("+COLUMN_SERVICERATE+"), "+ " FOREIGN KEY ("+USERID+")" +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";

    public static final  String  TABLE_5 = "CREATE TABLE " +
            TABLE_SERVICE_PROVIDER_AVAILABILITIES + "("
            + AVAILABILITIES_ID + " INTEGER PRIMARY KEY," + COLUMN_INITIAL_DATE
            + " TEXT," + COLUMN_INITIAL_TIME + " TEXT,"+ FINAL_TIME +
            " TEXT," + SERVICEPROVIDERD + " TEXT," + " FOREIGN KEY ("+SERVICEPROVIDERD+") " +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";


    public static final String TABLE_6 = "CREATE TABLE " +
            TABLE_BOOKING + "("
            + BOOKING_ID + " INTEGER PRIMARY KEY," + HOME_OWNER_ID +
            " TEXT," +BOOKING_TIME +
            " TEXT," +BOOKING_RATING +
            " INTEGER," + SERVICE_PROVIDER_ID + " TEXT," +" FOREIGN KEY ("+BOOKING_RATING+")" +
            " REFERENCES "+ TABLE_FEEDBACK+ " ("+COLUMN_RATING+"), "+" FOREIGN KEY ("+HOME_OWNER_ID+")" +
            " REFERENCES "+ TABLE_USERS+ " ("+COLUMN_ID+"), "+ " FOREIGN KEY ("+SERVICE_PROVIDER_ID+")" +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";

    public static final String TABLE_7 = "CREATE TABLE " +
            TABLE_FEEDBACK + "("
            + RATING_ID + " INTEGER PRIMARY KEY," + COLUMN_COMMENTS
            + " TEXT," + FEEDBACK_BOOKING_ID + " TEXT,"+  COLUMN_RATING + " INTEGER,"+ HOMEWONERIDBOOKING +
            " TEXT," + SERVICEPROVIDER_IDBOOKING + " TEXT," +" FOREIGN KEY ("+HOMEWONERIDBOOKING+")" +
            " REFERENCES "+ TABLE_USERS+ " ("+COLUMN_ID+"), "+ " FOREIGN KEY ("+FEEDBACK_BOOKING_ID+")" +
            " REFERENCES "+ TABLE_BOOKING+ " ("+BOOKING_ID+"), "+" FOREIGN KEY ("+SERVICEPROVIDER_IDBOOKING+")" +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";

    public static final  String  TABLE_8 = "CREATE TABLE " +
            TABLE_SERVICE_PROVIDER_RATING + "("
            + _ID + " INTEGER PRIMARY KEY," + SERVICE_PROVIDER_RATED
            + " TEXT," + RATING_TO_SERVICE_PROVIDER + " TEXT,"
            + " FOREIGN KEY ("+SERVICE_PROVIDER_RATED+") " +
            "REFERENCES "+TABLE_USERS+"("+COLUMN_ID+"));";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_1);
        db.execSQL(TABLE_2);
        db.execSQL(TABLE_3);
        db.execSQL(TABLE_4);
        db.execSQL(TABLE_5);
        db.execSQL(TABLE_6);
        db.execSQL(TABLE_7);
        db.execSQL(TABLE_8);
    }

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_AVAILABILITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_RATING);
        onCreate(db);
    }

    public void addAvailabilities (HashMap<String, String> dateInfo, int serviceProviderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INITIAL_DATE, dateInfo.get("initDate"));
        values.put(COLUMN_INITIAL_TIME,dateInfo.get("initTime"));
        values.put(FINAL_TIME,dateInfo.get("finalTime"));
        values.put(SERVICEPROVIDERD,serviceProviderId);
        db.insert(TABLE_SERVICE_PROVIDER_AVAILABILITIES,null,values);
        db.close();
    }

    public List<User> findServiceProviderByService(String serviceName){

        SQLiteDatabase db = this.getWritableDatabase();


       String  query = "SELECT * FROM " + TABLE_USERS +
                        " LEFT JOIN " + TABLE_SERVICE_PROVIDER_SERVICES + " ON "
                        + TABLE_USERS + "." + COLUMN_ID + " = " +
                TABLE_SERVICE_PROVIDER_SERVICES + "." +  USERID +
                " WHERE " + TABLE_SERVICE_PROVIDER_SERVICES + "." + COLUMN_SERVICENAMEPROVIDERTABLE + " = \"" + serviceName + "\"";

        Cursor cursor = db.rawQuery(query,null);
        List<User> users = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6),cursor.getString(7), cursor.getString(8)) ;
            users.add(user);
        }

        db.close();

        return users;
    }

    public List<User> findServiceProviderByRating(String rating){

        SQLiteDatabase db = this.getWritableDatabase();

        String  query = "SELECT * FROM " + TABLE_USERS +
                " LEFT JOIN " + TABLE_SERVICE_PROVIDER_RATING + " ON "
                + TABLE_USERS + "." + COLUMN_ID + " = " +
                TABLE_SERVICE_PROVIDER_RATING + "." +  SERVICE_PROVIDER_RATED +
                " WHERE " + TABLE_SERVICE_PROVIDER_RATING + "." + RATING_TO_SERVICE_PROVIDER + " >= \"" + rating + "\"";


        Cursor cursor = db.rawQuery(query,null);
        List<User> users = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6),cursor.getString(7), cursor.getString(8)) ;
            users.add(user);
        }

        db.close();

        return users;
    }

    public void InsertServiceProviderRating (String rating, String serviceProviderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long value = findServiceProviderRate(serviceProviderId );
        if (value == -2) {
            SQLiteDatabase db2 = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SERVICE_PROVIDER_RATED, serviceProviderId);
            values.put(RATING_TO_SERVICE_PROVIDER, rating);
            long rowId = db2.insert(TABLE_SERVICE_PROVIDER_RATING,null,values);
            db2.close();
        } else {
            SQLiteDatabase db3 = this.getWritableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put(RATING_TO_SERVICE_PROVIDER, rating);
            newValues.put(SERVICE_PROVIDER_RATED, rating);
            db.update(TABLE_SERVICE_PROVIDER_RATING, newValues, SERVICE_PROVIDER_RATED + " = ?" , new String[]{serviceProviderId});
            db3.close();
        }
    }

    public long findServiceProviderRate(String  serviceProviderId ) {
        String query = "Select * FROM " + TABLE_SERVICE_PROVIDER_RATING + " WHERE " +
                SERVICE_PROVIDER_RATED+" = \""+ serviceProviderId + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        long variable  = -2;

        if (cursor.moveToFirst()) {
            variable = 1;
            cursor.close();
        } else {
            variable = -2;
        }
        db.close();
        return variable;
    }

    public List<Booking> findServiceProviderBooking(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_BOOKING + " WHERE " +
                HOME_OWNER_ID + " = \"" + id + "\"";

        Cursor cursor = db.rawQuery(query,null);
        List<Booking> bookings = new ArrayList<>();
        while(cursor.moveToNext()){
            User usertemp  = findUserById(cursor.getString(4));
            Booking booking = new Booking(cursor.getString(0),cursor.getString(1), usertemp.getFirstName(), cursor.getString(2)) ;
            if (cursor.getString(3) != null) {
                booking.setRate(cursor.getString(3));
            }
            bookings.add(booking);
        }

        db.close();
        return bookings;
    }


    public long addServicesToUser (Services services, int serviceProviderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Services tempServices = findServices(services.getName(), serviceProviderId );
        if (tempServices != null) {
            return -2;
        } else {
            SQLiteDatabase db2 = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SERVICENAMEPROVIDERTABLE, services.getName());
            values.put(COLUMN_SERVICEPRICEPROVIDERTABLE, services.gethourRate());
            values.put(USERID,serviceProviderId);
            long rowId = db2.insert(TABLE_SERVICE_PROVIDER_SERVICES,null,values);
            db2.close();
            return rowId;
        }
    }

    public Services findService(String username) {
        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + username + "\"" + "OR "+
                COLUMN_SERVICENAME + " = \"" + Character.toUpperCase(username.charAt(0)) + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Services services = null;

        if (cursor.moveToFirst()) {
            services = new Services(cursor.getString(1), cursor.getString(2));
            cursor.close();
        } else {
            services = null;
        }
        db.close();
        return services;
    }

    public Services findServices(String serviceName, int serviceProviderId ) {
        String query = "Select * FROM " + TABLE_SERVICE_PROVIDER_SERVICES + " WHERE " +
                USERID+" = \""+ serviceProviderId + "\""+ "AND "+ COLUMN_SERVICENAMEPROVIDERTABLE + " = \"" + serviceName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Services services = null;

        if (cursor.moveToFirst()) {
            services = new Services(cursor.getString(1), cursor.getString(2));
            cursor.close();
        } else {
            services = null;
        }
        db.close();
        return services;
    }

    public User findUserById(String Id) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_ID + " = \"" + Id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }


    public User findUser(String username) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        User user = null;

        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7));
            cursor.close();
        } else {
            user = null;
        }

        db.close();

        return user;
    }

    public User findUserByName(String name) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_FIRSTNAME + " = \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7));
            cursor.close();
        } else {
            user = null;
        }
        db.close();

        return user;
    }

    public String calculateServiceProviderRating(String Id) {

        String query = "Select * FROM " + TABLE_FEEDBACK + " WHERE " +
                SERVICEPROVIDER_IDBOOKING + " = \"" + Id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        double calculatedRate = 0;

        ArrayList<Double> allRating = new ArrayList<>();
        double sum =  0;

        while (cursor.moveToNext()) {
            allRating.add(Double.parseDouble(cursor.getString(3)));
        }
        cursor.close();

        if (allRating.size() == 0) {
            calculatedRate = 0;
        } else {
            for (double foundRate : allRating) {
                sum += foundRate;
            }
            calculatedRate = sum/allRating.size();
        }
        db.close();
        return Double.toString(calculatedRate);
    }


    public Map findServiceProviderServices(int serviceProviderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =  "Select * FROM " + TABLE_SERVICE_PROVIDER_SERVICES + " WHERE " +
                USERID + " = \"" + serviceProviderId + "\"";
        Cursor cursor = db.rawQuery(query,null);
        Map<String, String> tableInfo = new HashMap<String, String>();
        while(cursor.moveToNext()){
            tableInfo.put(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICENAMEPROVIDERTABLE)), cursor.getString(cursor.getColumnIndex(COLUMN_SERVICEPRICEPROVIDERTABLE)));
        }
        return tableInfo;
    }

    public boolean deleteServiceProviderServices (String serviceName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+ TABLE_SERVICE_PROVIDER_SERVICES + " WHERE " +
                COLUMN_SERVICENAMEPROVIDERTABLE + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE_PROVIDER_SERVICES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public long addServices (Services services){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME, services.getName());
        values.put(COLUMN_SERVICERATE,services.gethourRate());
        long id = db.insert(TABLE_SERVICES,null,values);
        db.close();
        return id;
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
            values.put(COLUMN_USERNAME, user.getUsername());
            values.put(COLUMN_PASSWORD,user.getPassword());
            values.put(COLUMN_BIRTHDAY,user.getBirthday());
            values.put(COLUMN_USERTYPE, user.getUserType());
            values.put(COLUMN_POSTALCODE, user.getPostalCode());
            values.put(COLUMN_ADDRESS, user.getPostalCode());
            long id = db2.insert(TABLE_USERS,null,values);
            db2.close();
            return id;
        }
    }

    public long addFeedback (Feedback feedback){
        Feedback feedback1 = findFeedback(feedback.getBookingId());
        if (feedback1 != null) {
            return -2;
        } else {
            SQLiteDatabase db2 = this.getWritableDatabase();
            updateServiceRating (feedback);
            ContentValues values = new ContentValues();
            values.put(COLUMN_COMMENTS,feedback.getComments());
            values.put(COLUMN_RATING, feedback.getRatings());
            values.put(FEEDBACK_BOOKING_ID, feedback.getBookingId());
            values.put(HOMEWONERIDBOOKING,feedback.getHome_owner_id());
            values.put(SERVICEPROVIDER_IDBOOKING,feedback.getService_provider_id());
            long id = db2.insert(TABLE_FEEDBACK,null,values);
            db2.close();
            InsertServiceProviderRating (feedback.getRatings(), feedback.getService_provider_id());
            return id;
        }
    }

    public void updateServiceRating (Feedback feedback){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(BOOKING_RATING, feedback.getRatings() );
        db.update(TABLE_BOOKING, newValues, BOOKING_ID + " = ?" , new String[]{feedback.getBookingId()});
    }

    public void updateServiceProviderRate (String Id, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(RATING_TO_SERVICE_PROVIDER, rating);
        newValues.put(SERVICE_PROVIDER_RATED, rating);
        db.update(TABLE_SERVICE_PROVIDER_RATING, newValues, _ID + " = ?" , new String[]{Id});
    }

    public long addBooking (Booking booking){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HOME_OWNER_ID,booking.getHome_owner_id());
        values.put(SERVICE_PROVIDER_ID, booking.getService_provider_id());
        values.put(BOOKING_TIME,booking.getDateOfBooking());
        long id = db2.insert(TABLE_BOOKING,null,values);
        db2.close();
        return id;
    }


    public HashMap findServiceProviderInfo(String Id) {

        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("Rating", calculateServiceProviderRating(Id));
       String query = "Select * FROM " + TABLE_SERVICE_PROVIDER_INFO + " WHERE " +
                COLUMN_USERID + " = \"" + Id + "\"";
        SQLiteDatabase db2 = this.getWritableDatabase();


        Cursor cursor = db2.rawQuery(query, null);




        while (cursor.moveToNext()) {
            userInfo.put("CompanyName",cursor.getString(1));
            userInfo.put("Licensed",cursor.getString(2));
            userInfo.put("Expertise",cursor.getString(3));
            userInfo.put("ExperienceYears",cursor.getString(4));
            userInfo.put("PhoneNumber",cursor.getString(5));
        }
        cursor.close();
        db2.close();
        return userInfo;
    }

    public Feedback findFeedback(String  bookingId) {
        String query = "Select * FROM " + TABLE_FEEDBACK + " WHERE " +
                FEEDBACK_BOOKING_ID + " = \"" + bookingId + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Feedback feedback1 = null;

        if (cursor.moveToFirst()) {
            feedback1 = new Feedback(cursor.getString(0),cursor.getString(1), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5), cursor.getString(2));
            cursor.close();
        } else {
            feedback1 = null;
        }
        db.close();
        return feedback1;
    }

    public void ServiceProviderInfo (ServiceProvider serviceProvider, String serviceProviderId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANYNAME, serviceProvider.getCompanyName());
        values.put(COLUMN_EXPERIENCEYEARS,serviceProvider.getexperienceYears());
        values.put(COLUMN_LICENSE, serviceProvider.getLicenseName());
        values.put(COLUMN_EXPERTISE, serviceProvider.getExpertise());
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


    public List<Availability> findAvailabilities(int serviceProviderId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query =  "Select * FROM " + TABLE_SERVICE_PROVIDER_AVAILABILITIES + " WHERE " +
                SERVICEPROVIDERD + " = \"" + serviceProviderId + "\"";
        Cursor cursor = db.rawQuery(query,null);
        List<Availability> availabities = new ArrayList<>();
        while(cursor.moveToNext()){
            Availability availability = new Availability(cursor.getString(0),cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));
            availabities.add(availability);
        }
        return availabities;
    }

    public List<User> FindServiceProviderbyAvailability(HashMap<String, String> map){
        SQLiteDatabase db = this.getWritableDatabase();
        String query =  "Select * FROM " + TABLE_SERVICE_PROVIDER_AVAILABILITIES ;
        Cursor cursor = db.rawQuery(query,null);
        List<User> users = new ArrayList<>();
        String userInitialDate = map.get("initDate") + " "+map.get("initTime");
        String userFinalDate = map.get("initDate") + " "+map.get("finalTime");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        while(cursor.moveToNext()){
            String initialDate = cursor.getString(1) + " "+cursor.getString(2);
            String finalDate = cursor.getString(1)+ " "+ cursor.getString(3);
            try {
                Date initialdate = formatter1.parse(userInitialDate);
                Date finaldate = formatter1.parse(userFinalDate);
                Date serviceProviderInitialDate = formatter1.parse(initialDate);
                Date serviceProviderFinalDate = formatter1.parse(finalDate);
                if ( (initialdate.after(serviceProviderInitialDate) || initialdate.equals(serviceProviderInitialDate) ) && (serviceProviderFinalDate.after(finaldate) || serviceProviderFinalDate.equals(finaldate))) {
                    users.add(findUserById(cursor.getString(4)));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();

        return users;
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

    public void updateAvailability (HashMap<String, String> dateInfo, String availabilityId  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(COLUMN_INITIAL_DATE, dateInfo.get("initDate") );
        newValues.put(COLUMN_INITIAL_TIME, dateInfo.get("initTime"));
        newValues.put(FINAL_TIME, dateInfo.get("finalTime"));
        db.update(TABLE_SERVICE_PROVIDER_AVAILABILITIES, newValues, AVAILABILITIES_ID + " = ?" , new String[]{availabilityId});
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