package com.example.meal_app


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meal_app.categories.CategoryAdapter

import com.example.meal_app.favoris.FavorisAdapter
import com.example.meal_app.recipes.RecipeAdapter

import okhttp3.*


class MainActivity4 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var favorisAdapter: FavorisAdapter
    private lateinit var listfav : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        recyclerView = findViewById(R.id.recyclerViewFavoris)
        listfav = ArrayList<String>()
        val fav = intent.getStringExtra("fav")
        val prefsA = getSharedPreferences(fav, Context.MODE_PRIVATE)
        val prefsMap: Map<String,*> = prefsA.all
        for ((key, value) in prefsMap) {
            Log.d("PrefsMap", "$key = $value")
            if (value as Boolean)
            {
                Log.d("PrefsMap"," $value")
                listfav.add(key)
            }
        }

            favorisAdapter = FavorisAdapter(applicationContext, listfav)
            recyclerView.adapter = favorisAdapter
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)




    }

}
