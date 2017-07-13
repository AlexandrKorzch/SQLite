package com.korzh.user.sqlitetest.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 13.07.17.
 */

public class SQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLHelper";

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME  = "user";
    private static final String COLUMN_NAME  = "name";
    private static final String COLUMN_AGE  = "age";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "SQLHelper: ");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");

        db.execSQL(createTable());

        insertIntoTAble(db, "Vasya", 26);
        insertIntoTAble(db, "Kolya", 56);
        insertIntoTAble(db, "Roma", 29);

        selectFromTable(db);

        deleteFromTable(db);

        deleteTable(db);
    }


    private String createTable(){
        return "CREATE TABLE " + TABLE_NAME + " ("+COLUMN_NAME+" STRING, "+COLUMN_AGE+" INTEGER)";
    }

    private void insertIntoTAble(SQLiteDatabase db, String name, int age){
        Log.d(TAG, "insertIntoTAble: ");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        db.insert(TABLE_NAME, null, contentValues);
    }

    private void selectFromTable(SQLiteDatabase db) {
        Log.d(TAG, "selectFromTable: ");
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_NAME, COLUMN_AGE},
                COLUMN_NAME + " IN (?, ?)",
                new String[]{"Vasya", "Roma"},
                null,
                null,
                null);
        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                    Log.d(TAG, "selectFromTable: name - "+name+" age - "+age);
                }while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }


    private void deleteFromTable(SQLiteDatabase db) {
        Log.d(TAG, "deleteFromTable: ");
        db.delete(TABLE_NAME, COLUMN_NAME+" = ?", new String[]{"Kolya"});
    }

    private void deleteTable(SQLiteDatabase db){
        Log.d(TAG, "deleteTable: ");
        db.execSQL("DROP TABLE "+ TABLE_NAME);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: db = [" + db + "], oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");

    }

}
