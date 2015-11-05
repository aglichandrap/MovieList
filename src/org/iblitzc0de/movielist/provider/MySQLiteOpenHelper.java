package org.iblitzc0de.movielist.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.util.Log;
import org.iblitzc0de.movieapp2.BuildConfig;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_FILE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS movie ( _id INTEGER PRIMARY KEY AUTOINCREMENT, movie__movie_id INTEGER, backdrop_path TEXT, overview TEXT, release_date TEXT, poster_path TEXT, title TEXT, vote_average REAL , CONSTRAINT unique_id UNIQUE (movie__movie_id) ON CONFLICT REPLACE );";
    public static final String SQL_CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS review ( _id INTEGER PRIMARY KEY AUTOINCREMENT, review_id TEXT, author TEXT, content TEXT, url TEXT, review__movie_id INTEGER NOT NULL , CONSTRAINT fk_movie_id FOREIGN KEY (review__movie_id) REFERENCES movie (_id) ON DELETE CASCADE, CONSTRAINT unique_id UNIQUE (review_id) ON CONFLICT REPLACE );";
    public static final String SQL_CREATE_TABLE_VIDEO = "CREATE TABLE IF NOT EXISTS video ( _id INTEGER PRIMARY KEY AUTOINCREMENT, video_id TEXT, name TEXT, key TEXT, size INTEGER, type TEXT, video__movie_id INTEGER NOT NULL , CONSTRAINT fk_movie_id FOREIGN KEY (video__movie_id) REFERENCES movie (_id) ON DELETE CASCADE, CONSTRAINT unique_id UNIQUE (video_id) ON CONFLICT REPLACE );";
    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();
    private static MySQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MySQLiteOpenHelperCallbacks mOpenHelperCallbacks = new MySQLiteOpenHelperCallbacks();

    public static MySQLiteOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MySQLiteOpenHelper newInstance(Context context) {
        if (VERSION.SDK_INT < 11) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }

    private static MySQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context);
    }

    private MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @TargetApi(11)
    private static MySQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(11)
    private MySQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }
        this.mOpenHelperCallbacks.onPreCreate(this.mContext, db);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_REVIEW);
        db.execSQL(SQL_CREATE_TABLE_VIDEO);
        this.mOpenHelperCallbacks.onPostCreate(this.mContext, db);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        this.mOpenHelperCallbacks.onOpen(this.mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (VERSION.SDK_INT < 16) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(16)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.mOpenHelperCallbacks.onUpgrade(this.mContext, db, oldVersion, newVersion);
    }
}
