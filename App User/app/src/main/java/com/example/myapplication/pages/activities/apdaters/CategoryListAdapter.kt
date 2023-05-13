package com.example.myapplication.pages.activities.apdaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Category

class CategoryListAdapter(
    private var categoryList: ArrayList<Category>
): RecyclerView.Adapter<CategoryListAdapter.ViewHolder>(){
    var onItemClick:((Category)->Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val categoryImageIV: ImageView =listItemView.findViewById(R.id.categoryImageIV)
        val categoryNameTV: TextView = listItemView.findViewById(R.id.categoryName)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(categoryList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.item_product_category, parent, false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categoryList[position]
        val categoryName = holder.categoryNameTV
        categoryName.text = category.getName()
        val categoryImage = holder.categoryImageIV
//        categoryImage.setImageResource(category.image)
        Glide.with(holder.itemView)
            .load(category.getImage()).fitCenter()
            .into(categoryImage)
    }
}