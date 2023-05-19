package com.example.homework_2_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException

class ActivityCRUD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)



        //接收
        intent?.extras?.let {
            //接收上一頁的網址，擷取 video ID 放到 videoID 變數
            val YTURL = it.getString("key1")
            val videoID = YTURL?.substring(17)
            //val textViewVideoID = findViewById<TextView>(R.id.textViewVideoID)
            //textViewVideoID.text = URL

            //連上網路服務
            val client = OkHttpClient()

            //[GET] 用 YouTube API GET 影片內容
            //val textUrl = findViewById<TextView>(R.id.textUrl)
            val urlYTAPI = "https://www.googleapis.com/youtube/v3/videos?id=$videoID&part=snippet&key=AIzaSyDZF2AHLOUOUkwEX0xaaoG0UjUWNN4oJ3Q"

            val request = Request.Builder()
                .url(urlYTAPI)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        println(responseBody)

                        val gson = Gson()
                        val jsonObject = gson.fromJson(responseBody, JsonObject::class.java)

                        //取得 YouTube 影片的標題
                        val title = jsonObject
                            .getAsJsonArray("items")[0]
                            .asJsonObject
                            .getAsJsonObject("snippet")
                            .get("title")
                            .asString
                        println("標題---------${title}")

                        runOnUiThread {
                            findViewById<TextView>(R.id.textViewShowTitle).text = title.toString()
                        }
                    } else {
                        println("Request failed")
                        runOnUiThread{
                            findViewById<TextView>(R.id.textViewShowAll).text = "資料錯誤"
                        }
                    }
                }

            })
        }



    }
}