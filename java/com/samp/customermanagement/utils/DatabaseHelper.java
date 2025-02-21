package com.samp.customermanagement.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.samp.customermanagement.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "customer_db.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CUSTOMER = "customer";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_AGE = "AGE";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_SALARY = "SALARY";
    public static final String COLUMN_DELETE_DATE = "DELETE_DATE";
    private String[] allColumns = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_AGE,
            COLUMN_ADDRESS,
            COLUMN_SALARY,
            COLUMN_DELETE_DATE
    };
    private SQLiteDatabase database;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CUSTOMER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME +" TEXT NOT NULL, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_SALARY + " REAL, " +
                COLUMN_DELETE_DATE + " TEXT" +
                ")";
        db.execSQL(createTable);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public synchronized void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public void addCustomer(Customer customer) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, customer.getName());
        values.put(COLUMN_AGE, customer.getAge());
        values.put(COLUMN_ADDRESS, customer.getAddress());
        values.put(COLUMN_SALARY, customer.getSalary());
        List<String> lstColumnAdd = new ArrayList<>();
//        lstColumnAdd.add(Col)
        database.insert(TABLE_CUSTOMER, null, values);
        database.close();
    }

    public List<Customer> getAllCustomers(Boolean hasRemove) {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMER, allColumns, hasRemove ? null : COLUMN_DELETE_DATE + " IS NULL",
                null, null, null, DatabaseHelper.COLUMN_ID + " ASC");

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SALARY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DELETE_DATE))
                );
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return customers;
    }

    public Customer getCustomerById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER + " WHERE ID = ?", new String[]{String.valueOf(id)});
        Customer customer = null;
        if (cursor.moveToFirst()) {
            customer = new Customer(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SALARY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DELETE_DATE))
            );
        }
        cursor.close();
        db.close();
        return customer;
    }

    public void updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, customer.getName());
        values.put(COLUMN_AGE, customer.getAge());
        values.put(COLUMN_ADDRESS, customer.getAddress());
        values.put(COLUMN_SALARY, customer.getSalary());
        values.put(COLUMN_DELETE_DATE, customer.getDeleteDate());

        db.update(TABLE_CUSTOMER, values, COLUMN_ID + " = ?", new String[]{String.valueOf(customer.getId())});
        db.close();
    }

    public void increaseSalary(double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String sql = "UPDATE " + TABLE_CUSTOMER + " SET SALARY = SALARY + " + amount + " WHERE DELETE_DATE IS NULL";
        db.execSQL(sql);
        db.close();
    }

    public void logicallyDeleteCustomers(List<Integer> ids) {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());

        db.beginTransaction();
        try {
            for (int id : ids) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_DELETE_DATE, currentDate);
                db.update(TABLE_CUSTOMER, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public List<Customer> searchCustomers(String query) {
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER +
                " WHERE ( " + COLUMN_NAME + " LIKE ? OR " + COLUMN_ADDRESS + " LIKE ? )" +
                " AND DELETE_DATE IS NULL";
        String likeQuery = "%" + query + "%";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{likeQuery, likeQuery});

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SALARY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DELETE_DATE))
                );
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return customers;
    }


}
