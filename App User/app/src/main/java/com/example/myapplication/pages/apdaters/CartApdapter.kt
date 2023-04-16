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
import com.example.myapplication.modals.CartItem
import com.example.myapplication.pages.apdaters.interfaces.OnItemClickListener


class CartApdapter(
    private var productList: ArrayList<CartItem>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<CartApdapter.ViewHolder>() {
    private lateinit var view:View
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val productImageIV: ImageView = listItemView.findViewById(R.id.productImageIV)
        val productNameTV: TextView = listItemView.findViewById(R.id.productNameTV)
        val productPriceTV: TextView = listItemView.findViewById(R.id.productPriceTV)
        val productDescriptionTV: TextView = listItemView.findViewById(R.id.productDescriptionTV)
        val iconDelete: ImageView = listItemView.findViewById(R.id.btnDelete)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        view = inflater.inflate(R.layout.item_your_cart, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: CartItem = productList[position]
        val productName = holder.productNameTV
        val productImage = holder.productImageIV
        val productPrice = holder.productPriceTV
        val productDescription = holder.productDescriptionTV
        productName.text = product.getName()
        productPrice.text = product.getPrice().toString()
        productDescription.text = product.getDescription()
        Glide.with(holder.itemView)
            .load( product.getImage()).fitCenter()
            .into(productImage)


        holder.itemView.setOnClickListener{
           println(product)

            listener.onCartItemClickUpdate(position,product)

        }
        holder.iconDelete.setOnClickListener {
            listener.onCartItemClick(position,product)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addCartItem(cartItems:ArrayList<CartItem>){
        productList.apply {
            clear()
            productList.addAll(cartItems)
            notifyDataSetChanged()
        }
    }
}
