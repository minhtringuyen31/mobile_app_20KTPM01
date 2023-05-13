package com.example.appadmin.pages.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.modals.Category

class CategoryAdapter(private val context: Context, private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName = itemView.findViewById<TextView>(R.id.categoryName)
        val categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)
        val disable = itemView.findViewById<ImageView>(R.id.isDisableCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val orderView = inflater.inflate(R.layout.custom_category, parent, false)
        return ViewHolder(orderView)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categories[position]
        val categoryName = holder.categoryName
        categoryName.text = category.getName()
        val categoryImage = holder.categoryImage
        Glide.with(context).load(category.getImage()).into(categoryImage)
        val disable = holder.disable
        if (category.getIsDisable() == 1) {
            disable.setImageResource(R.drawable.baseline_disable_24)
        } else {
            disable.setImageResource(R.drawable.baseline_able_24)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditCategory::class.java)
            intent.putExtra("categoryId", category.getId().toString())
            context.startActivity(intent)
        }
    }
}