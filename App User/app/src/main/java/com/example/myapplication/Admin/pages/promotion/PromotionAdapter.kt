package com.example.myapplication.Admin.pages.promotion

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Admin.modals.Promotion
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class PromotionAdapter(private val context: Context, private val items: List<Promotion>) :
    RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val promotionImage = listItemView.findViewById<ImageView>(R.id.promotionImage)
        val promotionName = listItemView.findViewById<TextView>(R.id.promotionName)
        val promotionDiscount = listItemView.findViewById<TextView>(R.id.promotionDiscount)
        val promotionEndDate = listItemView.findViewById<TextView>(R.id.promotionEndDate)
        val promotionStatus = listItemView.findViewById<ImageView>(R.id.isDisablePromotion)
        val promotionQuantity = listItemView.findViewById<TextView>(R.id.promotionQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.custom_promotion, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promotion = items[position]
        val promotionImage = holder.promotionImage
        val promotionName = holder.promotionName
        val promotionDiscount = holder.promotionDiscount
        val promotionEndDate = holder.promotionEndDate
        val promotionStatus = holder.promotionStatus
        val promotionQuantity = holder.promotionQuantity

        Glide.with(context).load(promotion.getImage()).fitCenter().into(promotionImage)
        promotionName.text = promotion.getName()
        promotionDiscount.text = "Discount: " + promotion.getDiscount().toString() + "%"
        promotionEndDate.text = "Expired: " + LocalDateTime.parse(
            promotion.getEndDate().toString(),
            DateTimeFormatter.ISO_DATE_TIME
        )
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        promotionQuantity.text = promotion.getQuantity().toString()
        if (promotion.getIsDisable() == 1)
            promotionStatus.setImageResource(R.drawable.baseline_disable_24)
        else
            promotionStatus.setImageResource(R.drawable.baseline_able_24)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PromotionDetail::class.java)
            intent.putExtra("promotionId", promotion.getId().toString())
            context.startActivity(intent)
        }
    }

}