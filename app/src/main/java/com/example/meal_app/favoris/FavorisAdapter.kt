package com.example.meal_app.favoris

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.meal_app.R

class FavorisAdapter(
        private val context: Context,
        val favoris: ArrayList<String>
    ) : RecyclerView.Adapter<FavorisAdapter.FavorisViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorisViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_favoris, parent, false)
            return FavorisViewHolder(itemView)
        }


        override fun onBindViewHolder(holder: FavorisViewHolder, position: Int) {
            holder.nameTextView.setText(favoris.get(position))


        }

        override fun getItemCount(): Int {
            return favoris.count()
        }

        inner class FavorisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
            var nameTextView: TextView = itemView.findViewById(R.id.textview_favoris)

            override fun onClick(v: View?) {
                val position = adapterPosition
            }
        }

    }
