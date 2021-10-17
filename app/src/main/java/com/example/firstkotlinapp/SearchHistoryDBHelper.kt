package com.example.firstkotlinapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SearchHistoryDBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "SearchHistory.db"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "SearchHistory"
        private const val TAG = "SearchHistoryDBHelper"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(keyword TEXT PRIMARY KEY, date DATETIME);"
        Log.d(TAG, "DB onCreate")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE if exists $TABLE_NAME"

        Log.d(TAG, "DB onUpgrade")
        db?.execSQL(query)
        onCreate(db)
    }

    fun insertSearchHistory(searchHistory: SearchHistory?) {
        val query = "INSERT OR REPLACE INTO " + TABLE_NAME + " (keyword, date) values ('" +
                searchHistory?.keyword + "', '" + searchHistory?.dateTime + "')"
        writableDatabase.execSQL(query)
    }

    fun deleteSearchHistory(searchHistory: SearchHistory?) {
        val query =
            "DELETE FROM " + TABLE_NAME + " where keyword = '" + searchHistory?.keyword + "'"
        Log.e(TAG, "DB deleteSearchHistory")
        writableDatabase.execSQL(query)
    }
}