package com.example.loginuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class DBManager {

    private Context context;

    private SQLiteDatabase database;

    DBManager(Context c) {
        context = c;
    }

    void open() throws SQLException {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

//    public void close() {
//        dbHelper.close();
//    }

    void insert(String name, int isLogged) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.ISLOGGED, isLogged);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.ISLOGGED};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    void update(long _id, int isLogged) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ISLOGGED, isLogged);
        database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}

