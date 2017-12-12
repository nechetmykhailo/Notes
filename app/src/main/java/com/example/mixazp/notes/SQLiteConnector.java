package com.example.mixazp.notes;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;


class SQLiteConnector extends SQLiteOpenHelper {


    public SQLiteConnector(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try
        {
            sqLiteDatabase.execSQL("create table AddNotes (_id integer primary key autoincrement,"
                    + "name varchar(15),"
                    + "content varchar(100)," +
                    "dateNotes datetime )");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AddNotes");

        onCreate(sqLiteDatabase);

    }
}
