package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class OrderDetail : AppCompatActivity() {
    private var subTotalTV : TextView? = null
    private var discountTV : TextView? = null
    private var totalTV : TextView? = null
    private var orderDetailListRV : RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
    }

    private fun initUI(){
        subTotalTV = findViewById(R.id.subTotalValueTV)
        discountTV = findViewById(R.id.discountValueTV)
        totalTV = findViewById(R.id.totalValueTV)
        orderDetailListRV = findViewById(R.id.orderDetailRV)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(){

    }




}