package com.example.firstkotlinapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.databinding.ObservableArrayList

class SearchHistoryDBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "SearchHistory.db"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "SearchHistory"
        private const val COL_KEYWORD = "keyword"
        private const val COL_DATE = "date"
        private const val TAG = "SearchHistoryDBHelper"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS $TABLE_NAME" +
                "($COL_KEYWORD TEXT PRIMARY KEY, $COL_DATE DATETIME);"

        Log.d(TAG, "DB onCreate")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE if exists $TABLE_NAME"

        Log.d(TAG, "DB onUpgrade")
        db?.execSQL(query)
        onCreate(db)
    }

    fun readHistory(list: ObservableArrayList<SearchHistory>) {
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COL_DATE DESC"
        val cursor = writableDatabase.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val keyword = cursor.getString(cursor.getColumnIndex(COL_KEYWORD))
            val dateTime = cursor.getString(cursor.getColumnIndex(COL_DATE))
            val searchHistory = SearchHistory(keyword, dateTime)
            list.add(searchHistory)
        }
    }

    fun insertSearchHistory(searchHistory: SearchHistory?) {
        val query = "INSERT OR REPLACE INTO $TABLE_NAME ($COL_KEYWORD, $COL_DATE)" +
                "values ('" + searchHistory?.keyword + "', '" + searchHistory?.dateTime + "')"
        writableDatabase.execSQL(query)
    }

    fun deleteSearchHistory(searchHistory: SearchHistory?) {
        val query =
            "DELETE FROM $TABLE_NAME where $COL_KEYWORD = '" + searchHistory?.keyword + "'"
        writableDatabase.execSQL(query)
    }
}