package com.example.appadmin.pages.product

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.modals.Product

class ProductAdapter(private val context: Context, private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iconImageView = listItemView.findViewById<ImageView>(R.id.productImage)
        val nameTextView = listItemView.findViewById<TextView>(R.id.productName)
        val descTextView = listItemView.findViewById<TextView>(R.id.productDesc)
        val createdTextView = listItemView.findViewById<TextView>(R.id.productCreatedAt)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Product = items[position]
        val imageView = holder.iconImageView
        imageView.setImageResource(R.drawable.profile)
        val nameTv = holder.nameTextView
        nameTv.text = item.getName()
        val descTv = holder.descTextView
        descTv.text = item.getDescription()
        val dateTv = holder.createdTextView
        dateTv.text = item.getReleaseDate()
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditProduct::class.java)
            intent.putExtra("product_detail", item.toString())
            context.startActivity(intent)
        }
    }
}