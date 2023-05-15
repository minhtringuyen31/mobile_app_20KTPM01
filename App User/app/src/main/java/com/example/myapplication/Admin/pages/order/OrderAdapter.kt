package com.example.myapplication.Admin.pages.order

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.UserController
import com.example.myapplication.Admin.modals.Order
import com.example.myapplication.R


class OrderAdapter(private val context: Context, private val items: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val customerOrderName = listItemView.findViewById<TextView>(R.id.customerOrderName)
        val orderAddress = listItemView.findViewById<TextView>(R.id.orderAddress)
        val totalOrder = listItemView.findViewById<TextView>(R.id.totalOrder)
        val orderStatus = listItemView.findViewById<TextView>(R.id.orderStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val orderView = inflater.inflate(R.layout.custom_order, parent, false)
        return ViewHolder(orderView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Order = items[position]
        val customerOrderName = holder.customerOrderName
        item.getUserId()?.let {
            ViewModelProvider(context as AppCompatActivity)[UserController::class.java].getUser(it)
                .observe(context) { user ->
                    customerOrderName.text = user.getName()
                }
        }
        val orderAddress = holder.orderAddress
        orderAddress.text = item.getShippingAddress()
        val totalOrder = holder.totalOrder
        totalOrder.text = item.getTotal().toString()
        val orderStatus = holder.orderStatus
        if (item.getStatus() == 0) {
            orderStatus.text = "Đang xử lý"
        } else if (item.getStatus() == 1) {
            orderStatus.text = "Đang giao"
        } else if (item.getStatus() == 2) {
            orderStatus.text = "Đã giao"
        } else if (item.getStatus() == -1) {
            orderStatus.text = "Đã hủy"
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditOrder::class.java)
            intent.putExtra("orderId", item.getId().toString())
            intent.putExtra("order",item)
            context.startActivity(intent)
        }
    }
}