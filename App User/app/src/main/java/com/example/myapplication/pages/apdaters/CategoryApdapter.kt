package com.example.myapplication.pages.apdaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.pages.ProductList
import com.example.myapplication.pages.fragments.Homepage

class CategoryApdapter(private val context: Homepage, private val categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryApdapter.ViewHolder>() {
    lateinit var view:View

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.image_categories) as ImageView
        val name = view.findViewById(R.id.name_categories) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categories[position].getPathImage())
            .into(holder.imageView)
        //Glide và picasso
        holder.name.text = categories[position].getName();

        holder.itemView.setOnClickListener {
            val intent = Intent(
                view.context ,
                ProductList::class.java
            )
            view.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun addCategory(categories: ArrayList<Category>) {
        this.categories.apply {
            clear();
            addAll(categories)
        }
    }
}

