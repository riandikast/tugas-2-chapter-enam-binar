package com.binar.chapterenam.pertemuan2

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binar.chapterenam.R
import kotlinx.android.synthetic.main.activity_async_task.*


@Suppress("DEPRECATION")
class AsyncTask : AppCompatActivity() {
    lateinit var Konteks : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        Konteks = this
        contohAsynTask().execute()
    }
    inner class contohAsynTask : AsyncTask<Int, Void, String>(){
        lateinit var pdialog : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            pdialog = ProgressDialog(Konteks)
            pdialog.show()
        }

        override fun doInBackground(vararg p0: Int?): String {
            val count = 12 + 9
            return count.toString()
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textasyc.text = result
            pdialog.dismiss()
        }

    }


}