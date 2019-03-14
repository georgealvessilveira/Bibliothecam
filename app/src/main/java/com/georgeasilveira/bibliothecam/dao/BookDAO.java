package com.georgeasilveira.bibliothecam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.georgeasilveira.bibliothecam.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends SQLiteOpenHelper {

    public BookDAO(Context context) {
        super(context, "Bibliothecam", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Books(" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "author TEXT NOT NULL," +
                "page INTEGER NOT NULL," +
                "favorite INTEGER NOT NULL," +
                "location TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getDataFrom(book);
        db.insert("Books", null, data);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM Books;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<Book> books = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = new Book()
                    .setId(cursor.getLong(cursor.getColumnIndex("id")))
                    .setTitle(cursor.getString(cursor.getColumnIndex("title")))
                    .setAuthor(cursor.getString(cursor.getColumnIndex("author")))
                    .setPage(cursor.getInt(cursor.getColumnIndex("page")))
                    .setFavorite(cursor.getInt(cursor.getColumnIndex("favorite")))
                    .setLocation(cursor.getString(cursor.getColumnIndex("location")));
            books.add(book);
        }
        cursor.close();

        return books;
    }

    private ContentValues getDataFrom(Book book) {
        ContentValues data = new ContentValues();
        data.put("id", book.getId());
        data.put("title", book.getTitle());
        data.put("author", book.getAuthor());
        data.put("page", book.getPage());
        data.put("favorite", book.getFavorite());
        data.put("location", book.getLocation());
        return data;
    }
}
