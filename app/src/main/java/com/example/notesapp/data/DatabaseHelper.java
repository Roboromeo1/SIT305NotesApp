package com.example.notesapp.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.notesapp.model.Note;
import com.example.notesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.NOTE_NAME + " TEXT , " + Util.NOTE_CONTENT + " TEXT)";

        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);

    }

    public long insertNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_NAME, note.getName());
        contentValues.put(Util.NOTE_CONTENT, note.getContent());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close(); //to prevent memory leaks.
        return newRowId;
    }

    public List<Note> fetchNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> note_list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String note_name = cursor.getString(cursor.getColumnIndex(Util.NOTE_NAME));
                String note_content = cursor.getString(cursor.getColumnIndex(Util.NOTE_CONTENT));
                note_list.add(new Note(note_name, note_content));
                cursor.moveToNext();
            }
        }
        db.close();
        return note_list;
    }

    public Note fetchNote(String name, String content) {
        SQLiteDatabase db = this.getReadableDatabase();
        Note note;
        //Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.NOTE_NAME + "=" + name + "AND " + Util.NOTE_CONTENT + "=" + content, null);
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.NOTE_ID}, Util.NOTE_NAME + "=? and " + Util.NOTE_CONTENT + "=?",
            new String[] {name, content}, null, null, null);
        String note_name = cursor.getString(cursor.getColumnIndex(Util.NOTE_NAME));
        String note_content = cursor.getString(cursor.getColumnIndex(Util.NOTE_CONTENT));
        note = new Note(note_name, note_content);
        db.close();
        return note;
    }

    public void deleteNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.NOTE_NAME + "=?", new String[]{String.valueOf(note.getName())}); //delete note where the id is the one in the parameter
    }

    //updates the name and content and returns the passed in id.
    public boolean updateNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.NOTE_NAME, note.getName());
        contentValues.put(Util.NOTE_CONTENT, note.getContent());
        db.update(Util.TABLE_NAME, contentValues, Util.NOTE_NAME + "=?", new String[]{String.valueOf(note.getName())});
        db.close();
        return true;
    }

}
