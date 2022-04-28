package com.binar.chapterenam.pertemuan2

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.binar.chapterenam.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

class Coroutine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        contohCoroutine()
//        CoroutineScope(Dispatchers.Main).launch {
//
//        }
    }

    fun contohCoroutine(){
        GlobalScope.launch {
            delay(2000)
            textcor.text = "p"
        }

    }

    suspend fun getString(s :String):String{
        delay (1000)
        return s
    }
}