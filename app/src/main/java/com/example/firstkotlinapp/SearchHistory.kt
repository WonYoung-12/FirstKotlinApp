package com.example.firstkotlinapp

class SearchHistory {
    var searchEngine : String
    var keyWord : String
    var dateTime : String

    constructor(searchEngine: String, keyWord: String, dateTime: String) {
        this.searchEngine = searchEngine
        this.keyWord = keyWord
        this.dateTime = dateTime
    }
}