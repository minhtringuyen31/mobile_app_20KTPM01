package com.example.appadmin.pages.topping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.modals.Topping

class ToppingAdapter(private val context: Context, private val toppings: List<Topping>) :
    RecyclerView.Adapter<ToppingAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toppingName = itemView.findViewById<TextView>(R.id.toppingName)
        val toppingPrice = itemView.findViewById<TextView>(R.id.toppingPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val orderView = inflater.inflate(R.layout.custom_topping, parent, false)
        return ViewHolder(orderView)
    }

    override fun getItemCount(): Int {
        return toppings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topping: Topping = toppings[position]
        val toppingName = holder.toppingName
        toppingName.text = topping.getName()
        val toppingPrice = holder.toppingPrice
        toppingPrice.text = topping.getPrice().toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditTopping::class.java)
            intent.putExtra("toppingId", topping.getId().toString())
            context.startActivity(intent)
        }
    }
}