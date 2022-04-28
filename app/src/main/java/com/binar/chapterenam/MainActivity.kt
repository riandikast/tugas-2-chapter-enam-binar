package com.binar.chapterenam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.binar.chapterenam.pertemuan1.HitungBMI
import com.binar.chapterenam.pertemuan1.HitungUmur
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        contohThread()
        contohHandler()
//        contohHandlerThread()

    }

fun  contohThread(){
    Thread(Runnable {
        btn1.setOnClickListener {
            text.post({text.text = " Binar"})
        }
        text.post(Runnable{text.text = " Hello world"})
        text2.postDelayed(Runnable{text2.text = "Welcome p"}, 2000)
    }).start()

}

    fun contohHandler(){
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, HitungBMI::class.java))
        }, 3000)

        Handler(Looper.getMainLooper()).post(Runnable {
            text2.setText("Hello")
        })
    }

    fun contohHandlerThread(){
        val han = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val pesan2 = msg.obj as String
                text2.text = pesan2
            }
        }

        Thread(Runnable {
            btn1.setOnClickListener {
                val a = " Contoh "
                val pesan = Message.obtain()
                pesan.obj = a
                pesan.target = han
                pesan.sendToTarget()
            }
        }).start()
    }
}