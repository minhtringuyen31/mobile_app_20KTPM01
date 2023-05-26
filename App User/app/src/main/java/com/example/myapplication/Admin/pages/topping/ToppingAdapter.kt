package com.example.myapplication.Admin.pages.topping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.modals.Topping
import com.example.myapplication.R
import com.example.myapplication.utils.Utils

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
        val toppingPrice =   holder.toppingPrice
        toppingPrice.text = Utils.formatCurrency(topping.getPrice()!!) + " VND"
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ToppingDetail::class.java)
            intent.putExtra("toppingId", topping.getId().toString())
            context.startActivity(intent)
        }
    }
}