package ashutosh.letsplay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ashutosh.letsplay.data.tables.category.CategoryContract;
import ashutosh.letsplay.data.tables.song.SongContract;

import static ashutosh.letsplay.data.tables.song.SongProvider.Tables;

public class ItemsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "letsplay.db";
    private static final int DATABASE_VERSION = 4;

    public ItemsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.SONGS + " ("
                + SongContract.SongColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SongContract.SongColumns.GENRE + " TEXT,"
                + SongContract.SongColumns.TITLE + " TEXT NOT NULL,"
                + SongContract.SongColumns.COMPOSER + " TEXT,"
                + SongContract.SongColumns.TAB_AND_CHORD + " TEXT,"
                + SongContract.SongColumns.THUMB_URL + " TEXT,"
                + SongContract.SongColumns.PHOTO_URL + " TEXT,"
                + SongContract.SongColumns.ASPECT_RATIO + " REAL NOT NULL DEFAULT 1.5,"
                + SongContract.SongColumns.IS_FAVOURITE + " REAL NOT NULL DEFAULT 0,"
                + SongContract.SongColumns.TAGS + " TEXT,"
                + SongContract.SongColumns.LINK + " TEXT,"
                + SongContract.SongColumns.PUBLISHED_DATE + " INTEGER NOT NULL DEFAULT 0"
                + ")");

        db.execSQL("CREATE TABLE " + Tables.CATEGORY + " ("
                + CategoryContract.CategoryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoryContract.CategoryColumns.CATEGORY + " TEXT NOT NULL"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.CATEGORY);
        onCreate(db);
    }
}
