package com.example.myapplication.pages.apdaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Promotion
import com.example.myapplication.pages.fragments.Homepage
import com.smarteist.autoimageslider.SliderViewAdapter

class PromotionApdapter(private val context: Homepage, private val promotions: ArrayList<Promotion>) :
    SliderViewAdapter<PromotionApdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_promotion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView)
            .load(promotions[position].getImage())
            .into(viewHolder.imageView)


    }

    override fun getCount(): Int = promotions.size

    class ViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.showPromotions)
    }

    fun addPromotions(promotions: ArrayList<Promotion>) {
        this.promotions.apply {
            clear()
            addAll(promotions)
        }
    }
}

