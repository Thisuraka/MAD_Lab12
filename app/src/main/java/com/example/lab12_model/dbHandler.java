package com.example.lab12_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {

    //=============================== declaration area ======================================

    private static final int VERSION = 1;
    private static final String DB_NAME = "lab12db";
    private static final String TABLE_1 = "user"; //User table
    private static final String TABLE_2 = "message"; //Message table

    //  TABLE_1 Column names
    private static final String UID = "uid";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String TYPE = "type";

    //  TABLE_2 Column names
    private static final String MID = "mid";
    private static final String USER = "user";
    private static final String SUBJECT = "subject";
    private static final String MESSAGE = "message";

    public dbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY_1 = "CREATE TABLE " + TABLE_1 + " " +
                "("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + PASSWORD + " TEXT,"
                + TYPE + " TEXT" +
                ");";

        String TABLE_CREATE_QUERY_2 = "CREATE TABLE " + TABLE_2 + " " +
                "("
                + MID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER + " TEXT,"
                + SUBJECT + " TEXT,"
                + MESSAGE + " TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY_1);
        db.execSQL(TABLE_CREATE_QUERY_2);
    }

    //drop and recreate on upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY_1 = "DROP TABLE IF EXISTS " + TABLE_1;
        String DROP_TABLE_QUERY_2 = "DROP TABLE IF EXISTS " + TABLE_2;

        db.execSQL(DROP_TABLE_QUERY_1);
        db.execSQL(DROP_TABLE_QUERY_2);

        onCreate(db);
    }

    //=============================== Create new user ======================================
    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, user.getName());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(TYPE, user.getType());

        sqLiteDatabase.insert(TABLE_1, null, contentValues);

        sqLiteDatabase.close();
    }

    //=============================== Login Validate ======================================
    public User loginVal(String username, String password) {

        SQLiteDatabase db = getWritableDatabase();

        String[] columns = {UID, NAME, PASSWORD, TYPE};
        String selection = NAME + " = ?" + " AND " + PASSWORD + " = ?"; // selection criteria

        String[] selectionArgs = {username, password};  // selection argument

        Cursor cursor = db.query(TABLE_1, new String[]{UID, NAME, PASSWORD, TYPE},
                NAME + " = ?" + " AND " + PASSWORD + " = ?", selectionArgs
                , null, null, null);

        User user;
        if (cursor != null) {
            cursor.moveToFirst();
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            return user;
        }
        return null;
    }

    //=============================== Create new message ======================================
    public void addMessage(Message message) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(USER, message.getUser());
        contentValues.put(SUBJECT, message.getSubject());
        contentValues.put(MESSAGE, message.getMessage());

        sqLiteDatabase.insert(TABLE_2, null, contentValues);

        sqLiteDatabase.close();
    }

    //=============================== Select all messages ======================================
    // Get all messages into a list
    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_2;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                Message message = new Message();

                message.setMid(cursor.getInt(0));
                message.setUser(cursor.getString(1));
                message.setSubject(cursor.getString(2));
                message.setMessage(cursor.getString(3));

                messages.add(message);
            } while (cursor.moveToNext());
        }
        return messages;
    }
}


