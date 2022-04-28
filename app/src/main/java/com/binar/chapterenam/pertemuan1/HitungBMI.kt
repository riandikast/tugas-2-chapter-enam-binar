package com.binar.chapterenam.pertemuan1

import android.app.ProgressDialog
import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.binar.chapterenam.R
import kotlinx.android.synthetic.main.activity_async_task.*
import kotlinx.android.synthetic.main.activity_hitung_bmi.*
import kotlinx.android.synthetic.main.activity_hitung_bmi.text2
import kotlinx.android.synthetic.main.activity_hitung_umur.*
import kotlinx.android.synthetic.main.activity_hitung_umur.btnhitung
import kotlinx.android.synthetic.main.activity_hitung_umur.output
import kotlinx.android.synthetic.main.activity_hitung_umur.output2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt
import kotlin.properties.Delegates

@Suppress("DEPRECATION")
class HitungBMI : AppCompatActivity() {
    lateinit var hasil : String
    var bmi by Delegates.notNull<Double>()
    lateinit var Konteks : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitung_bmi)
        Konteks = this
        checkData()
//        thread()
//        handlerThread()
        btnhitung.setOnClickListener {
            contohAsynTask2().execute()
        }
        }


    fun thread(){
        Thread(Runnable {
            btnhitung.setOnClickListener {
                val bb = text1.text.toString().toDouble()
                val tinggi = text2.text.toString().toDouble()

                val m = (tinggi * tinggi)
                val convertToMeter = tinggi/100
                val cm = convertToMeter * convertToMeter

                if (tinggi < 10){
                    bmi = (bb / m)
                }else {
                    bmi = (bb / cm)
                }

                if (bmi < 18.5){
                    hasil = "Kurus"
                }else if (bmi >= 18.5 && bmi < 25){
                    hasil = "Normal"
                }else if (bmi >= 25 && bmi < 30){
                    hasil = "Overweight"
                }else{
                    hasil = "Obesitas"
                }
                output.post(
                    Runnable {  output.text = hasil})
            }
        }).start()
    }

    fun handlerThread(){
        val han = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val pesanHasil = msg.obj as String
                output2.text = pesanHasil
            }
        }

        Thread(Runnable {
            btnhitung2.setOnClickListener {
                val bb = text3.text.toString().toDouble()
                val tinggi = text4.text.toString().toDouble()

                val m = (tinggi * tinggi)
                val convertToMeter = tinggi/100
                val cm = convertToMeter * convertToMeter

                if (tinggi < 10){
                    bmi = (bb / m)
                }else {
                    bmi = (bb / cm)
                }

                if (bmi < 18.5){
                    hasil = "Kurus"
                }else if (bmi >= 18.5 && bmi < 25){
                    hasil = "Normal"
                }else if (bmi >= 25 && bmi < 30){
                    hasil = "Overweight"
                }else{
                    hasil = "Obesitas"
                }
                val pesan = Message.obtain()
                pesan.obj = hasil
                pesan.target = han
                pesan.sendToTarget()
            }
        }).start()
    }


    fun checkData(){
        val editText1 = findViewById<EditText>(R.id.text1)
        val editText2 = findViewById<EditText>(R.id.text2)
        val editText3 = findViewById<EditText>(R.id.text3)
        val editText4 = findViewById<EditText>(R.id.text4)
        val confirm = findViewById<Button>(R.id.btnhitung)
        val confirm2 = findViewById<Button>(R.id.btnhitung2)

        val textWatcher = object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val txt1 = text1.text.toString().trim()
                val txt2 = text2.text.toString().trim()
                val txt3 = text3.text.toString().trim()
                val txt4 = text4.text.toString().trim()


                confirm.isEnabled = txt1.isNotEmpty() && txt2.isNotEmpty()
                confirm2.isEnabled = txt3.isNotEmpty() && txt4.isNotEmpty()

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        }

        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)
        editText3.addTextChangedListener(textWatcher)
        editText4.addTextChangedListener(textWatcher)
    }

    inner class contohAsynTask2 : AsyncTask<Int, Void, String>(){
        lateinit var pdialog : ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            pdialog = ProgressDialog(Konteks)
            pdialog.show()
        }

        override fun doInBackground(vararg p0: Int?): String {

                val bb = text1.text.toString().toDouble()
                val tinggi = text2.text.toString().toDouble()

                val m = (tinggi * tinggi)
                val convertToMeter = tinggi / 100
                val cm = convertToMeter * convertToMeter



                if (tinggi < 10) {
                    bmi = (bb / m)
                } else {
                    bmi = (bb / cm)
                }

                if (bmi < 18.5) {
                    hasil = "Kurus"
                } else if (bmi >= 18.5 && bmi < 25) {
                    hasil = "Normal"
                } else if (bmi >= 25 && bmi < 30) {
                    hasil = "Overweight"
                } else {
                    hasil = "Obesitas"
                }

            return hasil
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            output.text = result
            pdialog.dismiss()
        }

    }

}