package com.example.myapplication.pages.apdaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Product
import com.example.myapplication.utils.Utils

class ProductListAdapter(
    private var productList: ArrayList<Product>,
    private var isLinearLayoutManager: Boolean
): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var onItemClick:((Product) -> Unit)? = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val productImageIV: ImageView = listItemView.findViewById(R.id.productImageIV)
        val productNameTV: TextView = listItemView.findViewById(R.id.productNameTV)
        val productPriceTV: TextView = listItemView.findViewById(R.id.productPriceTV)
        val productDescriptionTV: TextView = listItemView.findViewById(R.id.productDescriptionTV)

        init {
            listItemView.setOnClickListener{
                onItemClick?.invoke(productList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var productView: View? = null
        if (isLinearLayoutManager) {
            productView = inflater.inflate(R.layout.item_product_list_linear_layout, parent, false)
        } else {
            productView = inflater.inflate(R.layout.item_product_list_grid_layout, parent, false)
        }
        return ViewHolder(productView)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = productList[position]
        val productName = holder.productNameTV
        val productImage = holder.productImageIV
        val productPrice = holder.productPriceTV
        val productDescription = holder.productDescriptionTV
        productName.text = product.getName()
        productPrice.text = Utils.formatCurrency(product.getPrice_L().toDouble())
        productDescription.text = product.getDescription()
        Glide.with(holder.itemView)
            .load( product.getImage()).fitCenter()
            .into(productImage)
    }
}
