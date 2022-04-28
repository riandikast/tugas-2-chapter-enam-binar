package com.binar.chapterenam.pertemuan1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.binar.chapterenam.R
import kotlinx.android.synthetic.main.activity_hitung_umur.*
import kotlinx.android.synthetic.main.activity_main.*

class HitungUmur : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitung_umur)
        thread()


    }

    fun thread(){
        Thread(Runnable {
            btnhitung.setOnClickListener {
                val nama = nama.text.toString()
                val lahir = umur.text.toString()
                val usia = 2022 - lahir.toInt()
                output.post(
                    Runnable {  output.text = nama})
                output2.post(
                    Runnable {  output2.text = usia.toString()})
            }
        }).start()
    }




}