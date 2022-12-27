package com.example.meal_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meal_app.categories.CategoryAdapter
import com.example.meal_app.categories.CategoryResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() , CategoryAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var categoryResponse:CategoryResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView_categories)

        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")

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
                    categoryResponse = gson.fromJson(it, CategoryResponse::class.java)
                    categoryResponse.categories?.let { it1 ->
                        runOnUiThread {
                            categoriesAdapter = CategoryAdapter(applicationContext,it1,this@MainActivity)
                            recyclerView.adapter = categoriesAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }
                    Log.d("OKHTTP", "Got " + categoryResponse.categories?.count() + " results")
                }
                }
            })

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        if (categoryResponse.categories?.get(position)?.isLiked==false){
            getSharedPreferences("Categories", Context.MODE_MULTI_PROCESS).edit().apply{
                putBoolean(categoryResponse.categories?.get(position)?.idCategory,true)
                apply()
            }
            categoryResponse.categories?.get(position)?.isLiked=true
            categoriesAdapter.notifyItemChanged(position)
        }
        else {
            getSharedPreferences("Categories", Context.MODE_PRIVATE).edit().apply{
                putBoolean(categoryResponse.categories?.get(position)?.idCategory,false)
                apply()
            }
            categoryResponse.categories?.get(position)?.isLiked=false
            categoriesAdapter.notifyItemChanged(position)
        }

    }

}