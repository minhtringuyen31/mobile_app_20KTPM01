package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.OrderProductDetail
import com.example.myapplication.utils.Utils

class OrderProductListAdapter(
    private var orderProductList: ArrayList<OrderProductDetail>,
    private var status:Int,
    private var isRating:Int,
): RecyclerView.Adapter<OrderProductListAdapter.ViewHolder>(){
    var onRatingClick:((OrderProductDetail) -> Unit)? = null
    var onBuyAgainClick:((OrderProductDetail) -> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val orderProductNameTV : TextView = listItemView.findViewById(R.id.orderDetailItemNameTV)
        val orderProductSizeTV : TextView = listItemView.findViewById(R.id.orderDetailItemSizeTV)
        val orderProductQuantityTV : TextView = listItemView.findViewById(R.id.orderDetailItemQuantityTV)
        val orderProductToppingTV : TextView = listItemView.findViewById(R.id.orderDetailItemToppingTV)
        val orderProductTotalTV : TextView = listItemView.findViewById(R.id.orderDetailItemPriceTV)
        val orderProductImageIV : ImageView = listItemView.findViewById(R.id.orderDetailItemImg)
        val orderProductRatingBtn : TextView = listItemView.findViewById(R.id.orderDetailItemRatingBtn)
        val orderProductBuyAgainBtn : TextView = listItemView.findViewById(R.id.orderDetailItemBuyAgainBtn)


        init {
            if(status==2&&isRating==1){
                orderProductRatingBtn.visibility =View.VISIBLE
            }
        }
        init {

            orderProductRatingBtn.setOnClickListener {



                onRatingClick?.invoke(orderProductList[position])
            }

        }

        init {
            orderProductBuyAgainBtn.setOnClickListener {
                onRatingClick?.invoke(orderProductList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val orderProductItemView = inflater.inflate(R.layout.item_detail_order, parent, false)
        return ViewHolder(orderProductItemView)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return orderProductList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val orderProductItem: OrderProductDetail = orderProductList[position]
        val itemName = holder.orderProductNameTV
        val itemSize = holder.orderProductSizeTV
        val itemQuantity = holder.orderProductQuantityTV
        val itemTopping = holder.orderProductToppingTV
        val itemTotal = holder.orderProductTotalTV
        val itemImg = holder.orderProductImageIV

        itemName.text = orderProductItem.getProductName()
        itemSize.text = orderProductItem.getSize()
        itemQuantity.text = "x"+orderProductItem.getQuantity().toString()

        if(orderProductItem.getTopping().isNotEmpty())
        {
            itemTopping.text = orderProductItem.getTopping()
        }
        else
        {
            itemTopping.text = "Không có topping"
        }
        itemTotal.text =  Utils.formatCurrency( orderProductItem.getPrice()) +" đ"
        Glide.with(holder.itemView)
            .load(orderProductItem.getProductImg()).fitCenter()
            .into(itemImg)



    }

    @SuppressLint("NotifyDataSetChanged")
    fun addOrderProduct(orderProduct:ArrayList<OrderProductDetail>){
        orderProductList.apply {
            clear()
            orderProductList.addAll(orderProduct)
            notifyDataSetChanged()
        }

    }
}