package com.example.quoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.ViewModel.MainViewModel
import com.example.quoteapp.ViewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText : TextView
         get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
         get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //passing application context because it is not destroyed on configuration change
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }
    fun setQuote(quote: Quotes){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: android.view.View) {
        setQuote(mainViewModel.previousQuote())
    }
    fun onNext(view: android.view.View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: android.view.View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}