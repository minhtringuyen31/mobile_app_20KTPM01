package com.example.myapplication.pages.apdaters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Category
import com.example.myapplication.pages.fragments.Homepage
import com.example.myapplication.pages.fragments.Order
import com.example.myapplication.pages.fragments.ProductDetail

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
            .load(categories[position].getImage()).fitCenter()
            .into(holder.imageView)
        //Glide và picasso
        holder.name.text = categories[position].getName();

        holder.itemView.setOnClickListener {


            val categoryList = Order()
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, categoryList).addToBackStack(null)
                .commit()


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

