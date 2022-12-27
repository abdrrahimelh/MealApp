package com.example.meal_app.recipes


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal_app.MainActivity2
import com.example.meal_app.MainActivity3
import com.example.meal_app.R
import com.example.meal_app.categories.CategoryViewHolder


class RecipeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView = itemView.findViewById(R.id.textview_name)
    var recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
    var like_button: ImageView = itemView.findViewById(R.id.like_recipe_image)
}

class RecipeAdapter(private val context: Context, val recipes: List<Recipe>): RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            holder.nameTextView.setText(recipes.get(position).strMeal)
            with(holder) {
                Glide.with(context)
                    .load(recipes.get(position).strMealThumb)
                    .into(recipeImage)

            }
        holder.recipeImage.setOnClickListener{
            val intent = Intent(context, MainActivity3::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("strMeal",recipes.get(position).strMeal)
            context.startActivity(intent)
        }
            holder.like_button.setOnClickListener {
                holder.like_button.setImageResource(R.drawable.star_on)
            }


    }

    override fun getItemCount(): Int {
        return recipes.count()
    }
}