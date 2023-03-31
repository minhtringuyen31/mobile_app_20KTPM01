package com.example.appadmin.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.DashboardItems
import com.example.appadmin.R
import com.example.appadmin.account.Accounts
import com.example.appadmin.category.Categories
import com.example.appadmin.order.Orders
import com.example.appadmin.product.Products

class DashboardAdapter(private val context: Context, private val items: List<DashboardItems>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    private lateinit var intent : Intent
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iconImageView = listItemView.findViewById<ImageView>(R.id.dashboardItemImage)
        val nameTextView = listItemView.findViewById<TextView>(R.id.dashboardItemTv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.custom_dashboard_item, parent, false)
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
                    intent = Intent(context, Accounts::class.java)
                }
                3 -> {
                    intent = Intent(context, Orders::class.java)
                }
            }
            context.startActivity(intent)
        }
    }
}