package com.example.myapplication.Admin.pages.statistics

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.OrderController
import com.example.myapplication.Admin.modals.Order
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.Admin.pages.order.OrderAdapter
import com.example.myapplication.R
import com.example.myapplication.utils.Utils

class detail_statistic : AppCompatActivity() {
    private lateinit var orderList: List<Order>
    private lateinit var layoutEmptyAdmin:LinearLayout
    private lateinit var totalTV:TextView;
    private var total:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_statistic)
        val rvOrder = findViewById<RecyclerView>(R.id.orderRV)
        layoutEmptyAdmin=findViewById(R.id.layoutEmptyAdmin)
        totalTV = findViewById(R.id.totalPrice)
        val orderSpinner = findViewById<Spinner>(R.id.orderSpinner)
        val orderStatusList = mutableListOf<String>()
        orderStatusList.add("Tất cả")
        orderStatusList.add("Thống kê theo ngày")
        orderStatusList.add("Thống kê theo tuần")
        orderStatusList.add("Thống kê theo tháng")
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
        orderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                if (position == 0) {
                    println("Date")
                    ViewModelProvider(this@detail_statistic)[OrderController::class.java].getAllOrder().observe(this@detail_statistic) {

                        val orders = it
                        orderList = it
                        val adapter = OrderAdapter(this@detail_statistic, orders)
                        rvOrder.adapter = adapter
                        totalTV.text = "Tổng tiền: "+   Utils.formatCurrency(total) + " VND"
                        adapter.notifyDataSetChanged();
                        if(adapter.itemCount==0){
                            println("rỗng")
                            rvOrder.visibility = View.GONE
                            layoutEmptyAdmin.visibility = View.VISIBLE
                        }
                        else{
                            println("rỗng")
                            layoutEmptyAdmin.visibility = View.GONE
                            rvOrder.visibility = View.VISIBLE
                        }
                    }
                }  else if (position == 1) {
                    println("Week")
                    total=0.0
                    ViewModelProvider(this@detail_statistic)[OrderController::class.java].getAllOrderByDate().observe(this@detail_statistic) {
                        val orders = it
                        orderList = it
                        println(orders)
                        val adapter = OrderAdapter(this@detail_statistic, orders.filter {
                            it.getStatus() == 2
                        }
                        )
                        orders.filter {
                            it.getStatus() == 2
                        }.forEach {
                            total += it.getTotal()!!;
                        }
                        totalTV.text = "Tổng tiền: "+   Utils.formatCurrency(total) +" VND"

                        rvOrder.adapter = adapter
                        adapter.notifyDataSetChanged();
                        if(adapter.itemCount==0){
                            println("rỗng")
                            rvOrder.visibility = View.GONE
                            layoutEmptyAdmin.visibility = View.VISIBLE
                        }
                        else{
                            println("rỗng")
                            layoutEmptyAdmin.visibility = View.GONE
                            rvOrder.visibility = View.VISIBLE
                        }

                    }
                }
                else if (position == 2) {
                    println("Week")
                    total=0.0
                    ViewModelProvider(this@detail_statistic)[OrderController::class.java].getAllOrderByWeek().observe(this@detail_statistic) {

                        val orders = it
                        orderList = it
                        val adapter = OrderAdapter(this@detail_statistic, orders.filter { it.getStatus() ==2 })
                        orders.filter {
                            it.getStatus() == 2
                        }.forEach {
                            total += it.getTotal()!!;
                        }
                        totalTV.text = "Tổng tiền: "+   Utils.formatCurrency(total) + " VND"
                        rvOrder.adapter = adapter
                        adapter.notifyDataSetChanged();
                        if(adapter.itemCount==0){
                            println("rỗng")
                            rvOrder.visibility = View.GONE
                            layoutEmptyAdmin.visibility = View.VISIBLE
                        }
                        else{
                            println("rỗng")
                            layoutEmptyAdmin.visibility = View.GONE
                            rvOrder.visibility = View.VISIBLE
                        }

                    }
                } else {
                    println("Month")
                    total=0.0
                    ViewModelProvider(this@detail_statistic)[OrderController::class.java].getAllOrderByMonth().observe(this@detail_statistic) {
                        val orders = it
                        orderList = it
                        val adapter = OrderAdapter(this@detail_statistic, orders.filter { it.getStatus() ==2 })
                        orders.filter {
                            it.getStatus() == 2
                        }.forEach {
                            total += it.getTotal()!!;
                        }
                        totalTV.text = "Tổng tiền: "+   Utils.formatCurrency(total)+" VND"
                        rvOrder.adapter = adapter
                        adapter.notifyDataSetChanged();
                        if(adapter.itemCount==0){
                            println("rỗng")
                            rvOrder.visibility = View.GONE
                            layoutEmptyAdmin.visibility = View.VISIBLE
                        }
                        else{
                            println("rỗng")
                            layoutEmptyAdmin.visibility = View.GONE
                            rvOrder.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }
}