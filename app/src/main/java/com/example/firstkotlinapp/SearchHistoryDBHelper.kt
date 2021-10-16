package com.example.firstkotlinapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SearchHistoryDBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE if not exists SearchHistory (" +
                "_id integer autoincrement, " +
                "keyword text primary key, date DATETIME);";

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE if exists SearchHistory"

        db?.execSQL(query)
        onCreate(db)
    }

    fun insertSearchHistory(searchHistory: SearchHistory?) {
        val query = "INSERT OR REPLACE INTO SearchHistory (keyword, date) values (" +
                searchHistory?.keyword + ", " + searchHistory?.dateTime + ")"
        writableDatabase.execSQL(query)
    }
}