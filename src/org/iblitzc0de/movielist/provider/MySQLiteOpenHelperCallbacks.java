package org.iblitzc0de.movielist.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.iblitzc0de.movieapp2.BuildConfig;

public class MySQLiteOpenHelperCallbacks {
    private static final String TAG = MySQLiteOpenHelperCallbacks.class.getSimpleName();

    public void onOpen(Context context, SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onOpen");
        }
    }

    public void onPreCreate(Context context, SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPreCreate");
        }
    }

    public void onPostCreate(Context context, SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPostCreate");
        }
    }

    public void onUpgrade(Context context, SQLiteDatabase db, int oldVersion, int newVersion) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        }
    }
}
