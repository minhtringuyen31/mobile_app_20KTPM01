package com.example.myapplication.Admin.pages.order

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.OrderController
import com.example.myapplication.Admin.modals.Order
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.Admin.pages.dashboard.SocketReceiver
import com.example.myapplication.R

class Orders : AppCompatActivity() {
    private lateinit var orderList: List<Order>
    private val socketReceiver = SocketReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val intentFilter = IntentFilter("confirmSocket")
        registerReceiver(socketReceiver, intentFilter)
        println("new intetnt by order")
        val rvOrder = findViewById<RecyclerView>(R.id.orderRV)
        val orderSpinner = findViewById<Spinner>(R.id.orderSpinner)
        val orderStatusList = mutableListOf<String>()
        orderStatusList.add("Tất cả")
        orderStatusList.add("Đang xử lý")
        orderStatusList.add("Đang giao")
        orderStatusList.add("Đã giao")
        orderStatusList.add("Đã hủy")
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            orderStatusList
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            orderSpinner.adapter = adapter
        }
        findViewById<Button>(R.id.backOrderBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)

        }
        rvOrder.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ViewModelProvider(this)[OrderController::class.java].getAllOrder().observe(this) {
            val orders = it
            orderList = it

            val adapter = OrderAdapter(this, orders)
            rvOrder.adapter = adapter
        }
        orderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                if (position == 0) {
                    val adapter = OrderAdapter(this@Orders, orderList)
                    rvOrder.adapter = adapter
                } else if (position == 4) {
                    val adapter =
                        OrderAdapter(
                            this@Orders,
                            orderList.filter { it.getStatus() == position - 5 })
                    rvOrder.adapter = adapter
                } else {
                    val adapter = OrderAdapter(
                        this@Orders,
                        orderList.filter { it.getStatus() == position - 1 })
                    rvOrder.adapter = adapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}