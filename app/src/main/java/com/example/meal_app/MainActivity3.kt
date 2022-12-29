package com.example.meal_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meal_app.description.DescriptionResponse
import com.example.meal_app.recipes.RecipeAdapter
import com.example.meal_app.recipes.RecipeResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity3 : AppCompatActivity() {
    private  lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        val strMeal = intent.getStringExtra("strMeal")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textView = findViewById(R.id.descriptionName)


        val description = "52772"

        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772")

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
                    Log.d("OKHTTP", "Got " + descriptionResponse.description?.count() +  " results")
                    textView.text = "HELLOOOOO"
                }

    }
})
    }
}