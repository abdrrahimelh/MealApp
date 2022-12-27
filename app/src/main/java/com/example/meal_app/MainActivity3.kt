package com.example.meal_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meal_app.categories.CategoryAdapter
import com.example.meal_app.categories.CategoryResponse
import com.example.meal_app.recipes.Description
import com.example.meal_app.recipes.DescriptionAdapter
import com.example.meal_app.recipes.DescriptionResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity3 : AppCompatActivity() {
    private lateinit var DescriptionAdapter: DescriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val description = 52772

        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i="+ description)

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val gson = Gson()
                    val descriptionResponse = gson.fromJson(it, DescriptionResponse::class.java)
                    descriptionResponse.description?.let { it1 ->
                        runOnUiThread {
                            DescriptionAdapter = DescriptionAdapter(applicationContext,it1)
                        }

                    }
                    Log.d("OKHTTP", "Got results")
                }
            }
        })

    }


}