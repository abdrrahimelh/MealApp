package com.example.meal_app

import android.content.Intent
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meal_app.recipes.RecipeAdapter
import com.example.meal_app.recipes.RecipeResponse
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity2 : AppCompatActivity(),RecipeAdapter.OnItemClickListener  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipesAdapter: RecipeAdapter
    private lateinit var recipeResponse:RecipeResponse
    private lateinit var loader: CircularProgressIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        val category = intent.getStringExtra("category")
        //val category = "Beef"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recyclerView = findViewById(R.id.recyclerView_recipes)
        loader=findViewById(R.id.RecipeLoader)
        val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=" +
                category)

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
                    Thread(Runnable {
                        this@MainActivity2.runOnUiThread(java.lang.Runnable {
                            loader.hide()
                        })
                    }).start()


                    val gson = Gson()
                    recipeResponse = gson.fromJson(it, RecipeResponse::class.java)
                    recipeResponse.recipes?.let { it1 ->
                        runOnUiThread {
                            recipesAdapter = RecipeAdapter(applicationContext,it1,this@MainActivity2)
                            recyclerView.adapter = recipesAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }
                    Log.d("OKHTTP", "Got " + recipeResponse.recipes?.count() + " results")
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.action_one) {
            val intent = Intent(this@MainActivity2, MainActivity4::class.java)
            intent.putExtra("fav","Categories");
            startActivity(intent)
            return true
        }
        if (id == R.id.action_two) {
            val intent = Intent(this@MainActivity2, MainActivity4::class.java)
            intent.putExtra("fav","Recipes");
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)

    }
    override fun onItemClick(position: Int) {

        if (recipeResponse.recipes?.get(position)?.isLiked==false){
            getSharedPreferences("Recipes", Context.MODE_PRIVATE).edit().apply{
                putBoolean(recipeResponse.recipes?.get(position)?.strMeal,true)
                apply()
            }
            recipeResponse.recipes?.get(position)?.isLiked=true
            recipesAdapter.notifyItemChanged(position)
        }
        else {
            getSharedPreferences("Recipes", Context.MODE_PRIVATE).edit().apply{
                putBoolean(recipeResponse.recipes?.get(position)?.strMeal,false)
                apply()
            }
            recipeResponse.recipes?.get(position)?.isLiked=false
            recipesAdapter.notifyItemChanged(position)
        }

    }
}