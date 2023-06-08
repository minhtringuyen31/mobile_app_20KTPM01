package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Order
import com.example.myapplication.utils.Utils


class OrderListAdapter(
    private var orderList: ArrayList<Order>,
    private var status:Int
): RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    var onItemClick:((Order)-> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val orderId : TextView = listItemView.findViewById(R.id.orderIdTV)
        val orderAddressShipping : TextView = listItemView.findViewById(R.id.orderAddressShippingTV)
        val orderStatus : TextView = listItemView.findViewById(R.id.orderStatusTV)
        val orderDate : TextView = listItemView.findViewById(R.id.orderDateTV)
        val orderPrice : TextView = listItemView.findViewById(R.id.orderPriceTV)
        val orderDetailBtn : Button = listItemView.findViewById(R.id.orderDetailBtn)

//        init{
//            listItemView.setOnClickListener{
//                onItemClick?.invoke(orderList[position])
//            }
//        }

        init {
            orderDetailBtn.setOnClickListener {
                onItemClick?.invoke(orderList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var onGoingOrderView =  inflater.inflate(R.layout.item_on_going_order, parent, false)
        if(status==0)
        {
             onGoingOrderView = inflater.inflate(R.layout.item_on_going_order, parent, false)
        }
        else if(status==1) {
             onGoingOrderView = inflater.inflate(R.layout.item_confirm_order, parent, false)
        }
        else if(status==2) {
             onGoingOrderView = inflater.inflate(R.layout.item_success_order, parent, false)
        }
        else if(status==-1) {
             onGoingOrderView = inflater.inflate(R.layout.item_cancel_order, parent, false)
        }
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

        orderId.text = "Mã đơn hàng "+ onGoingOrder.getId().toString().hashCode()
        orderAddressShipping.text = onGoingOrder.getShippingAddress()
        orderDate.text =  Utils.convertDate( onGoingOrder.getOrderDate())
        orderPrice.text = onGoingOrder.getTotal().toString()

//        orderAddressShipping.text = onGoingOrder.getShippingAddress()
        if(onGoingOrder.getStatus() == 0){
            orderStatus.text = "Đơn hàng chờ xác nhận"
        } else if(onGoingOrder.getStatus() == 1){
            orderStatus.text = "Xác nhận đơn hàng!Đang trên đường tới bạn!"
        }
        else if(onGoingOrder.getStatus() == 2){
            orderStatus.text = "Đơn hàng giao thành công"
        }
        else if(onGoingOrder.getStatus() == -1){
            orderStatus.text = "Đơn hàng bị huỷ"
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