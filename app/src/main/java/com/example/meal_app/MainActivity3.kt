package com.example.meal_app

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.meal_app.description.DescriptionResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL


class MainActivity3 : AppCompatActivity() {
    private  lateinit var textViewInstructions: TextView
    private  lateinit var textYoutube: TextView
    private  lateinit var textViewCategory: TextView
    private  lateinit var textViewRecipe: TextView
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val idMeal = intent.getStringExtra("idMeal")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textViewInstructions = findViewById(R.id.Instructions)
        textViewInstructions.setMovementMethod(ScrollingMovementMethod())
        textYoutube = findViewById(R.id.URL)
        textViewCategory= findViewById(R.id.Category)
        textViewRecipe = findViewById(R.id.Recipe)


        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i="+ idMeal)

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
                    Log.d("OKHTTP", "Got " + descriptionResponse.description?.get(0)?.strCategory +  " results")

                    runOnUiThread {

                        textViewInstructions.text  = descriptionResponse.description?.get(0)?.strInstructions;
                        textYoutube.text = descriptionResponse.description?.get(0)?.strYoutube;
                        textViewCategory.text = descriptionResponse.description?.get(0)?.strCategory;
                        textViewRecipe.text = descriptionResponse.description?.get(0)?.strMeal;


                    }
                }

    }
})
    }
}