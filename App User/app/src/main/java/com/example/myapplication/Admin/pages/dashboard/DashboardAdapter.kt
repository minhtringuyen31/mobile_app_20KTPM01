package com.example.myapplication.Admin.pages.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.modals.DashboardItems
import com.example.myapplication.Admin.pages.category.Categories
import com.example.myapplication.Admin.pages.order.Orders
import com.example.myapplication.Admin.pages.product.Products
import com.example.myapplication.Admin.pages.promotion.Promotions
import com.example.myapplication.Admin.pages.rating.Ratings
import com.example.myapplication.Admin.pages.statistics.Statistics
import com.example.myapplication.Admin.pages.topping.Toppings
import com.example.myapplication.Admin.pages.user.Users
import com.example.myapplication.R


class DashboardAdapter(private val context: Context, private val items: List<DashboardItems>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    private lateinit var intent: Intent

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iconImageView = listItemView.findViewById<ImageView>(R.id.dashboardItemImage)
        val nameTextView = listItemView.findViewById<TextView>(R.id.dashboardItemTv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.items, parent, false)

        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DashboardItems = items[position]
        val imageView = holder.iconImageView
        imageView.setImageResource(item.image)
        val nameTv = holder.nameTextView
        nameTv.text = item.name
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    intent = Intent(context, Products::class.java)
                }
                1 -> {
                    intent = Intent(context, Categories::class.java)
                }
                2 -> {
                    intent = Intent(context, Users::class.java)
                }
                3 -> {
                    intent = Intent(context, Orders::class.java)
                }
                4 -> {
                    intent = Intent(context, Toppings::class.java)
                }
                5 -> {
                    intent = Intent(context, Promotions::class.java)
                }
                6 -> {
                    intent = Intent(context, Ratings::class.java)
                }
                7 -> {
                    intent = Intent(context, Statistics::class.java)
                }
            }
            context.startActivity(intent)
        }
    }
}