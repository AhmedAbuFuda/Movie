package com.example.movie.datasource.local.db;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
        private static final String TAG = "DdHelpler";

        public DbHelper(@Nullable Context context) {
            super(context, DbConfig.DbName, null, DbConfig.DbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //create tables
            //create table TableName (_ID integer primary key autoincrement,
//        note_text text)
            String sql = "create table " + DbConfig.TableName + " ( " + DbConfig.COL_ID + " integer primary key autoincrement, " + DbConfig.COL_TEXT + " text, " +
                    DbConfig.COL_DATE + " text, " + DbConfig.COL_POSTER + " text)";
            sqLiteDatabase.execSQL(sql);
            Log.d(TAG, "onCreate: ");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = "drop table IF EXISTS " + DbConfig.TableName;
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.d(TAG, "onUpgrade: ");

        }
}