package com.example.appadmin.pages.product

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.modals.Product

class ProductAdapter(private val context: Context, private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iconImageView: ImageView = listItemView.findViewById(R.id.productImage)
        val nameTextView: TextView = listItemView.findViewById(R.id.productName)
        val salesTextView: TextView = listItemView.findViewById(R.id.productSales)
        val statusTextView: TextView = listItemView.findViewById(R.id.productStatus)
        val disabledTextView: ImageView =
            listItemView.findViewById(R.id.isDisableProduct)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.custom_product, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Product = items[position]
        val imageView = holder.iconImageView
        Glide.with(context).load(item.getImage()).fitCenter().into(imageView)
        val nameTv = holder.nameTextView
        nameTv.text = item.getName()
        val salesTv = holder.salesTextView
        salesTv.text = "Đã bán: " + item.getSales().toString()
        val statusTv = holder.statusTextView
        if (item.getStatus() == 1)
            statusTv.text = "Còn hàng"
        else
            statusTv.text = "Hết hàng"
        val disabledTv = holder.disabledTextView
        if (item.getIsDisable() == 1)
            disabledTv.setImageResource(R.drawable.baseline_disable_24)
        else
            disabledTv.setImageResource(R.drawable.baseline_able_24)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetail::class.java)
            intent.putExtra("product_id", item.getId().toString())
            context.startActivity(intent)
        }
    }
}