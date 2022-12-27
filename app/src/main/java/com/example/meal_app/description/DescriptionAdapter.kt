package com.example.meal_app.recipes


import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class DescriptionViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView = itemView.findViewById(com.example.meal_app.R.id.descriptionname)
}

class DescriptionAdapter(private val context: Context, val description: Description ): RecyclerView.Adapter<DescriptionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(com.example.meal_app.R.layout.item_Description, parent, false)
        return DescriptionViewHolder(itemView)

    }



}