package com.example.thirdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ShoeStore.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SHOES = "shoes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IN_STOCK = "in_stock";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SHOES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BRAND + " TEXT, " +
                    COLUMN_SIZE + " INTEGER, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_IN_STOCK + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOES);
        onCreate(db);
    }

    public long insertShoe(Shoe shoe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BRAND, shoe.getBrand());
        values.put(COLUMN_SIZE, shoe.getSize());
        values.put(COLUMN_PRICE, shoe.getPrice());
        values.put(COLUMN_IN_STOCK, shoe.isInStock() ? 1 : 0);
        long newRowId = db.insert(TABLE_SHOES, null, values);
        db.close();
        return newRowId;
    }

    public List<Shoe> getAllShoes() {
        List<Shoe> shoeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SHOES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String brand = cursor.getString(cursor.getColumnIndex(COLUMN_BRAND));
                int size = cursor.getInt(cursor.getColumnIndex(COLUMN_SIZE));
                double price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                boolean inStock = cursor.getInt(cursor.getColumnIndex(COLUMN_IN_STOCK)) == 1;

                Shoe shoe = new Shoe(brand, size, inStock, price);
                shoeList.add(shoe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return shoeList;
    }
}