package com.example.myapplication.Admin.pages.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.modals.OrderProduct
import com.example.myapplication.R


class OrderProductAdapter(
    private val context: Context,
    private val list: List<OrderProduct>
) : RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val orderProductName = listItemView.findViewById<TextView>(R.id.orderProductName)
        val orderProductPrice = listItemView.findViewById<TextView>(R.id.productPrice)
        val orderProductQuantity = listItemView.findViewById<TextView>(R.id.productQuantity)
        val productNote = listItemView.findViewById<TextView>(R.id.productNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val orderProductView = inflater.inflate(R.layout.custom_order_product, parent, false)
        return ViewHolder(orderProductView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderProduct: OrderProduct = list[position]
        val orderProductName = holder.orderProductName
        orderProduct.getProductId()?.let {
            ViewModelProvider(context as AppCompatActivity)[ProductController::class.java].getProduct(
                it
            ).observe(context) {
                orderProductName.text = it.getName()
            }
        }
        val orderProductPrice = holder.orderProductPrice
        orderProductPrice.text = orderProduct.getPrice().toString()
        val orderProductQuantity = holder.orderProductQuantity
        orderProductQuantity.text = orderProduct.getQuantity().toString()
        val productNote = holder.productNote
        productNote.text = orderProduct.getNote()
    }
}
