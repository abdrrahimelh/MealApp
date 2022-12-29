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
    private  lateinit var textView: TextView
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val idMeal = intent.getStringExtra("idMeal")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textView = findViewById(R.id.descriptionName)
        textView.setMovementMethod(ScrollingMovementMethod())
        imageView = findViewById(R.id.image)


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
                    val text = descriptionResponse.description?.get(0)?.strCategory + " : " + "\n\n"  + descriptionResponse.description?.get(0)?.strMeal + "\n\n\n***********************\n\n\n" +
                            "You can click on the link below to see the full video on Youtube : "+ "\n" + descriptionResponse.description?.get(0)?.strYoutube + "\n\n\n***********************\n\n\n" +
                            "STEP-BY-STEP INSTRUCTIONS\n\n\n " + descriptionResponse.description?.get(0)?.strInstructions
                    runOnUiThread {

                        textView.text  = text;




                    }
                }

    }
})
    }
}