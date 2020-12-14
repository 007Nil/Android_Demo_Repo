package com.isabellatechsolutions.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_ID = "ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    //    This is called the first time a database is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE "+ CUSTOMER_TABLE+" ("+CUSTOMER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_NAME + " TEXT, "+CUSTOMER_AGE+" INT, "+ACTIVE_CUSTOMER+" BOOL)";

        db.execSQL(createTableStatement);

    }
//  This is called if the database version number changes. It prevents users apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME, customerModel.getCustomerName());
        cv.put(CUSTOMER_AGE, customerModel.getCustomerAge());
        cv.put(ACTIVE_CUSTOMER, customerModel.isActive());

        long insert = db.insert(CUSTOMER_TABLE,null,cv);

        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public List<CustomerModel> findAll(){
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean isActive = cursor.getInt(3) == 1 ? true: false;

                CustomerModel customerModel = new CustomerModel(customerID,customerName,customerAge,isActive);

                returnList.add(customerModel);
            }while(cursor.moveToNext());
        }else{
            // error occur do noting
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
