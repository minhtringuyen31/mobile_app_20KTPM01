package com.example.appadmin.pages.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.controllers.OrderController
import com.example.appadmin.pages.dashboard.Dashboard

class Orders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val rvOrder = findViewById<RecyclerView>(R.id.orderRV)
        findViewById<Button>(R.id.backOrderBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvOrder.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val orderViewAdapter = ViewModelProvider(this)[OrderController::class.java].getAllOrder().observe(this) {
            val orders = it

            val adapter = OrderAdapter(this, orders)
            rvOrder.adapter = adapter
        }
    }
}