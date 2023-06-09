package com.example.quoteapp.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quoteapp.Quotes
import com.google.gson.Gson

class MainViewModel( val context : Context): ViewModel() {
    private var quotelist: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quotelist = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuote() = quotelist[index]
    fun nextQuote() = quotelist[++index]
    fun previousQuote() = quotelist[--index]
}