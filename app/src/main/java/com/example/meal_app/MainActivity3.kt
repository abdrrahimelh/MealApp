package com.example.meal_app

import android.R.attr
import android.annotation.SuppressLint
import android.graphics.Paint
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
    private  lateinit var textViewIngredients: TextView
    private lateinit var Rl : RelativeLayout
    private lateinit var Rlingredients : RelativeLayout
    private lateinit var RlYoutube : RelativeLayout
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val idMeal = intent.getStringExtra("idMeal")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Rl = findViewById(R.id.relativelayoutInstructions);
        Rlingredients = findViewById(R.id.relativelayoutIngredients);
        RlYoutube = findViewById(R.id.YoutubeLayout);
        textViewInstructions = findViewById(R.id.Instructions)
        textViewInstructions.setMovementMethod(ScrollingMovementMethod())
        textYoutube = findViewById(R.id.URL)
        textYoutube.setMovementMethod(LinkMovementMethod.getInstance());
        textViewCategory= findViewById(R.id.Category)
        textViewRecipe = findViewById(R.id.Recipe)
        textViewIngredients = findViewById(R.id.Ingredients);


        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i="+ idMeal)
        fun calculateTextHeight(text: String, textSize: Int?): Int {
            val paint = Paint()
            if (textSize != null) {
                paint.textSize = textSize.toFloat()
            }
            val height = paint.fontMetrics.descent - paint.fontMetrics.ascent
            return height.toInt() +500 ;

        }
        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            @SuppressLint("SetTextI18n")
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

                           if (len != null) {
                               Rl.getLayoutParams().height = calculateTextHeight(instruction, len+count*10)
                           }


                        textViewInstructions.text  = "STEP BY STEP\n\n\n" + descriptionResponse.description?.get(0)?.strInstructions;
                        textViewCategory.text = descriptionResponse.description?.get(0)?.strCategory;
                        textViewRecipe.text = descriptionResponse.description?.get(0)?.strMeal;
                            if( descriptionResponse.description?.get(0)?.strYoutube != null || !(descriptionResponse.description?.get(0)?.strYoutube.equals("")) )
                            {
                                textYoutube.text = descriptionResponse.description?.get(0)?.strYoutube;

                            }
                        else {
                                RlYoutube.setVisibility( View.GONE);
                            }
                        val ingredientssb = StringBuilder() ;
                        ingredientssb.append("INGREDIENTS \n\n");
                        if( (descriptionResponse.description?.get(0)?.strIngredient1 != null && !(descriptionResponse.description?.get(0)?.strIngredient1.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure1 != null && !(descriptionResponse.description?.get(0)?.strMeasure1.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient1 + ": "+ descriptionResponse.description?.get(0)?.strMeasure1);
                            ingredientssb.append("\n");

                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient2 != null && !(descriptionResponse.description?.get(0)?.strIngredient2.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure2 != null && !(descriptionResponse.description?.get(0)?.strMeasure2.equals("")) ) )
                        {
                             ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient2 + ": "+ descriptionResponse.description?.get(0)?.strMeasure2);
                            ingredientssb.append("\n");

                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient3 != null && !(descriptionResponse.description?.get(0)?.strIngredient3.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure3 != null && !(descriptionResponse.description?.get(0)?.strMeasure3.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient1 + ": "+ descriptionResponse.description?.get(0)?.strMeasure3);
                            ingredientssb.append("\n");

                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient4 != null && !(descriptionResponse.description?.get(0)?.strIngredient4.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure4 != null && !(descriptionResponse.description?.get(0)?.strMeasure4.equals("")) ) )
                        {
                            ingredientssb.append( descriptionResponse.description?.get(0)?.strIngredient4 + ": "+ descriptionResponse.description?.get(0)?.strMeasure4);
                            ingredientssb.append("\n");

                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient5 != null && !(descriptionResponse.description?.get(0)?.strIngredient5.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure5 != null && !(descriptionResponse.description?.get(0)?.strMeasure5.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient5 + ": "+ descriptionResponse.description?.get(0)?.strMeasure5);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient6 != null && !(descriptionResponse.description?.get(0)?.strIngredient6.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure6 != null && !(descriptionResponse.description?.get(0)?.strMeasure6.equals("")) ) )
                    {
                        ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient6 + ": "+ descriptionResponse.description?.get(0)?.strMeasure6);
                        ingredientssb.append("\n");
                    }
                        if( (descriptionResponse.description?.get(0)?.strIngredient7 != null && !(descriptionResponse.description?.get(0)?.strIngredient7.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure7 != null && !(descriptionResponse.description?.get(0)?.strMeasure7.equals("")) ) )
                    {
                        ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient7 + ": "+ descriptionResponse.description?.get(0)?.strMeasure7);
                        ingredientssb.append("\n");
                    }
                        if( (descriptionResponse.description?.get(0)?.strIngredient8 != null && !(descriptionResponse.description?.get(0)?.strIngredient8.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure8 != null || !(descriptionResponse.description?.get(0)?.strMeasure8.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient8 + ": "+ descriptionResponse.description?.get(0)?.strMeasure8);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient9 != null && !(descriptionResponse.description?.get(0)?.strIngredient9.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure9 != null && !(descriptionResponse.description?.get(0)?.strMeasure9.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient9 + ": "+ descriptionResponse.description?.get(0)?.strMeasure9);
                            ingredientssb.append("\n");
                        }
                        ///////////*********************************


                        if( (descriptionResponse.description?.get(0)?.strIngredient10 != null && !(descriptionResponse.description?.get(0)?.strIngredient10.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure10 != null && !(descriptionResponse.description?.get(0)?.strMeasure10.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient10 + ": "+ descriptionResponse.description?.get(0)?.strMeasure10);
                            ingredientssb.append("\n");
                        }

                        if( (descriptionResponse.description?.get(0)?.strIngredient11 != null && !(descriptionResponse.description?.get(0)?.strIngredient11.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure11 != null && !(descriptionResponse.description?.get(0)?.strMeasure11.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient11 + ": "+ descriptionResponse.description?.get(0)?.strMeasure11);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient12 != null && !(descriptionResponse.description?.get(0)?.strIngredient12.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure12 != null && !(descriptionResponse.description?.get(0)?.strMeasure12.equals("")) ) )
                        {
                            ingredientssb.append( descriptionResponse.description?.get(0)?.strIngredient12 + ": "+ descriptionResponse.description?.get(0)?.strMeasure12);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient13 != null && !(descriptionResponse.description?.get(0)?.strIngredient13.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure13 != null && !(descriptionResponse.description?.get(0)?.strMeasure13.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient13 + ": "+ descriptionResponse.description?.get(0)?.strMeasure13);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient14 != null && !(descriptionResponse.description?.get(0)?.strIngredient14.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure14 != null && !(descriptionResponse.description?.get(0)?.strMeasure14.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient14 + ": "+ descriptionResponse.description?.get(0)?.strMeasure14);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient15 != null && !(descriptionResponse.description?.get(0)?.strIngredient15.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure15 != null && !(descriptionResponse.description?.get(0)?.strMeasure15.equals("")) ) )
                        {
                            ingredientssb.append( descriptionResponse.description?.get(0)?.strIngredient15 + ": "+ descriptionResponse.description?.get(0)?.strMeasure15);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient16 != null && !(descriptionResponse.description?.get(0)?.strIngredient16.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure16 != null && !(descriptionResponse.description?.get(0)?.strMeasure16.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient16 + ": hnaaa "+ descriptionResponse.description?.get(0)?.strMeasure16);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient17 != null && !(descriptionResponse.description?.get(0)?.strIngredient17.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure17 != null || !(descriptionResponse.description?.get(0)?.strMeasure17.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient17 + ": "+ descriptionResponse.description?.get(0)?.strMeasure17);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient18 != null && !(descriptionResponse.description?.get(0)?.strIngredient18.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure18 != null || !(descriptionResponse.description?.get(0)?.strMeasure18.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient18 + ": "+ descriptionResponse.description?.get(0)?.strMeasure18);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient19 != null && !(descriptionResponse.description?.get(0)?.strIngredient19.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure19 != null || !(descriptionResponse.description?.get(0)?.strMeasure19.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient19 + ": "+ descriptionResponse.description?.get(0)?.strMeasure19);
                            ingredientssb.append("\n");
                        }
                        if( (descriptionResponse.description?.get(0)?.strIngredient20 != null && !(descriptionResponse.description?.get(0)?.strIngredient20.equals(""))) && (descriptionResponse.description?.get(0)?.strMeasure20 != null || !(descriptionResponse.description?.get(0)?.strMeasure20.equals("")) ) )
                        {
                            ingredientssb.append(descriptionResponse.description?.get(0)?.strIngredient20 + ": "+ descriptionResponse.description?.get(0)?.strMeasure20);
                            ingredientssb.append("\n");

                        }

                        val lenIng = ingredientssb.toString()?.length
                        if (lenIng != null) {
                            Rlingredients.getLayoutParams().height = calculateTextHeight(ingredientssb.toString(), lenIng + 80)
                        }
                        ;
                        textViewIngredients.text=ingredientssb.toString();

                    }
                }

    }
})
    }
}