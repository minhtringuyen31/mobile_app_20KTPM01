package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Topping
import com.example.myapplication.pages.fragments.Checkout


class CheckoutApdater(
    private var items: ArrayList<CartItem>,
    private val listener: Checkout,
    mContext: Context
    ):  ArrayAdapter<Any?>(mContext, R.layout.item_checkout, items as ArrayList<*>) {
        private lateinit var view: View
    private class ViewHolder {
        lateinit var txtName: TextView
        lateinit var txtPrice: TextView
        lateinit var imageView: ImageView
        lateinit var topping: TextView

    }
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): CartItem {
        return items[position]
    }


    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        val result: View

        view=LayoutInflater.from(parent.context).inflate(R.layout.item_checkout, parent, false)
        if (convertView == null) {
            viewHolder = ViewHolder()

            viewHolder.txtName =
                view.findViewById(R.id.nameProductCheckout)
            viewHolder.txtPrice =
                view.findViewById(R.id.totalPriceCheckout)
            viewHolder.topping =
                view.findViewById(R.id.toppingCheckout)
            viewHolder.imageView =
                view.findViewById(R.id.imageItemCheckout)
            result = view
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }

        val item: CartItem = getItem(position)
        viewHolder.txtName.text = item.getName()
        viewHolder.txtPrice.text = item.getPrice().toString()
        viewHolder.topping.text = item.getTopping()
            Glide.with(view)
                .load(item.getImage()).fitCenter()
                .into(viewHolder.imageView)




        return result
    }
    fun addItems(promotions: ArrayList<CartItem>) {
        this.items.apply {
            clear()
            addAll(promotions)
            notifyDataSetChanged()
        }
    }

}