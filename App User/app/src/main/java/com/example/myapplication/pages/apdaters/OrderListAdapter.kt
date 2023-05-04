package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Order

class OrderListAdapter(
    private var orderList: ArrayList<Order>
): RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    var onItemClick:((Order)-> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val orderId : TextView = listItemView.findViewById(R.id.orderIdTV)
        val orderAddressShipping : TextView = listItemView.findViewById(R.id.orderAddressShippingTV)
        val orderStatus : TextView = listItemView.findViewById(R.id.orderStatusTV)
        val orderDate : TextView = listItemView.findViewById(R.id.orderDateTV)
        val orderPrice : TextView = listItemView.findViewById(R.id.orderPriceTV)

        init{
            listItemView.setOnClickListener{
                onItemClick?.invoke(orderList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val onGoingOrderView = inflater.inflate(R.layout.item_on_going_order, parent, false)
        return ViewHolder(onGoingOrderView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val onGoingOrder: Order  = orderList[position]
        val orderId = holder.orderId
        val orderAddressShipping = holder.orderAddressShipping
        val orderStatus = holder.orderStatus
        val orderDate = holder.orderDate
        val orderPrice = holder.orderPrice

        orderId.text = onGoingOrder.getId().toString()
        orderAddressShipping.text = onGoingOrder.getShippingAddress()
        orderDate.text = onGoingOrder.getOrderDate()
        orderPrice.text = onGoingOrder.getTotal().toString()

//        orderAddressShipping.text = onGoingOrder.getShippingAddress()
        if(onGoingOrder.getStatus() == 0){
            orderStatus.text = "Sản phẩm đang được giao"
        } else {
            orderStatus.text = "Sản phẩm đã giao thành công"
        }
//        orderDate.text = onGoingOrder.getOrderDate()
//        orderPrice.text = onGoingOrder.getTotal().toString()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addOrders(order:ArrayList<Order>){
        orderList.apply {
            clear()
            orderList.addAll(order)
            notifyDataSetChanged()
        }
    }
}