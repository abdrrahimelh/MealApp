package com.example.meal_app.categories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal_app.MainActivity2
import com.example.meal_app.R

class CategoryAdapter(private val context: Context,val categories: List<Category>,private val listener: OnItemClickListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val myItem = categories[position]
        holder.nameTextView.setText(categories.get(position).strCategory)
       with(holder) {
           Glide.with(context)
               .load(categories.get(position).strCategoryThumb)
               .into(categoryImage)

       }
        context.getSharedPreferences("Categories", Context.MODE_PRIVATE)
            .getBoolean(myItem.strCategory, false).let{
                if (!it){
                    holder.like_button.setImageResource(R.drawable.star_off)
                }
                else {
                    holder.like_button.setImageResource(R.drawable.star_on)
                }


            }

        holder.categoryImage.setOnClickListener{
            val intent = Intent(context,MainActivity2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("category",categories.get(position).strCategory)
            context.startActivity(intent)

        }



    }

    override fun getItemCount(): Int {
        return categories.count()
    }
    inner class CategoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var nameTextView: TextView = itemView.findViewById(R.id.textview_name)
        var categoryImage: ImageView = itemView.findViewById(R.id.category_image)
        var like_button: ImageView = itemView.findViewById(R.id.like_category_image)
        init {
            like_button.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}