package com.example.myapplication.Admin.pages.order

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.OrderController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Orders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

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