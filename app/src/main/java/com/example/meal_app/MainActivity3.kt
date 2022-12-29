package com.example.meal_app

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
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
    private lateinit var Rl : RelativeLayout
    private lateinit var RlYoutube : RelativeLayout
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val idMeal = intent.getStringExtra("idMeal")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Rl = findViewById(R.id.relativelayoutInstructions);
        RlYoutube = findViewById(R.id.YoutubeLayout);
        textViewInstructions = findViewById(R.id.Instructions)
        textViewInstructions.setMovementMethod(ScrollingMovementMethod())
        textYoutube = findViewById(R.id.URL)
        textYoutube.setMovementMethod(LinkMovementMethod.getInstance());
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
                    val instruction = descriptionResponse.description?.get(0)?.strInstructions
                    val len = instruction?.length
                    val lines: List<String>? = instruction?.split("\n")
                    val count = lines?.count()!!
                    runOnUiThread {
                        if (len != null ) {
                           for (i in 0..count-1)
                           Rl.getLayoutParams().height = len + lines[i].length

                        }
                        textViewInstructions.text  = "STEP BY STEP\n\n\n" + descriptionResponse.description?.get(0)?.strInstructions;
                        textViewCategory.text = descriptionResponse.description?.get(0)?.strCategory;
                        textViewRecipe.text = descriptionResponse.description?.get(0)?.strMeal;
                            if( descriptionResponse.description?.get(0)?.strYoutube != null && !(descriptionResponse.description?.get(0)?.strYoutube.equals("")) )
                            {
                                textYoutube.text = descriptionResponse.description?.get(0)?.strYoutube;

                            }
                        else {
                                RlYoutube.setVisibility( View.GONE);
                            }


                    }
                }

    }
})
    }
}