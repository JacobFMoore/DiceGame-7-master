package uk.ac.ljmu.asstwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Displays short hand for columns and names.
    //DO NOT TOUCH.
    public static final String TABLE_NAME = "ScoreTable";
    public static final String COL1 = "Username";
    public static final String COL2 = "Score";
    private static final String TAG = "DatabaseHelper";

    //DON'T TOUCH.
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    //Creates the table on the start of the running of the application.
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + TABLE_NAME + "(Username VARCHAR(256) PRIMARY KEY, Score INT null)";
        db.execSQL(CreateTable);
    }

    //Drops the table if the data is wiped from the emulator.
    //ONLY USER IF FRESH DATA EXAMPLE NEEDED.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Adds data to the SQlite database.
    public boolean addData(String item1, int item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);
        contentValues.put(COL2, item2);

        Log.d(TAG, "addData: Adding " + item1 + item2 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //gets the data from the SQLite database using a SQL code statement.
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY Score DESC LIMIT 10";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
