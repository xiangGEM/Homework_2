package com.example.homework_2_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //設定網址輸入框、按鈕的變數
        val buttonGo = findViewById<Button>(R.id.buttonGo)
        val editTextYTURL = findViewById<EditText>(R.id.editTextYTURL)

        //將網址變成字串放在 key1 變數傳到下一頁
        buttonGo.setOnClickListener {
            val intent = Intent(this, ActivityCRUD::class.java)
            val videoHTTP = editTextYTURL.text.toString()
            intent.putExtra("key1", videoHTTP)
            startActivity(intent)
        }



    }
}