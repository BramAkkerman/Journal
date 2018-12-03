package com.example.brama.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.BLOB;
import static java.sql.Types.INTEGER;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "journal";
    public static final String TABLE_NAME = "entries";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_DATE = "date";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_CONTENT + " TEXT,"
                    + COLUMN_MOOD + " TEXT,"
                    + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    private static EntryDatabase instance;

    public EntryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        // Add tests
        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_TITLE+","+COLUMN_CONTENT+","+
                COLUMN_MOOD+") values('WOW','No words','icon')");
        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_TITLE+","+COLUMN_CONTENT+","+
                COLUMN_MOOD+") values('Great day','For whoever likes headaches','sick_ill_trouble')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        EntryDatabase db = new EntryDatabase(context);
        return db;
    }

    public Cursor selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String allEntries = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(allEntries, null);
        return cursor;
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues addValues = new ContentValues();
        addValues.put(COLUMN_TITLE, entry.getTitle());
        addValues.put(COLUMN_CONTENT, entry.getContent());
        addValues.put(COLUMN_MOOD, entry.getMood());
        db.insert(TABLE_NAME, null, addValues);
    }

    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }
}
