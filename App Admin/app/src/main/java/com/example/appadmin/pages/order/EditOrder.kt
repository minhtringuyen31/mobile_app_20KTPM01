package com.example.appadmin.pages.order

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.OrderController
import com.example.appadmin.controllers.OrderProductController
import com.example.appadmin.controllers.UserController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditOrder : AppCompatActivity() {
    private var orderStatus: Int? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_order)

        val orderId = intent.getStringExtra("orderId")

        val orderProvider = ViewModelProvider(this)[OrderController::class.java]
        val orderProductProvider = ViewModelProvider(this)[OrderProductController::class.java]
        val userProvider = ViewModelProvider(this)[UserController::class.java]
        findViewById<Button>(R.id.orderCancelBtn).setOnClickListener {
            val intent = Intent(this, Orders::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.orderDetail_StatusBtn).setOnClickListener {
            if (orderStatus == 0) {
                orderProvider.changeDeliveredStatus(orderId!!.toInt()).observe(this) {

                }
            } else {
                orderProvider.changeDeliveringStatus(orderId!!.toInt()).observe(this) {

                }
            }
            val intent = Intent(this, Orders::class.java)
            startActivity(intent)
        }
        orderProvider.getOrder(orderId!!.toInt()).observe(this) {
            orderStatus = it.getStatus()
            userProvider.getUser(it.getUserId()!!).observe(this) { user ->
                findViewById<TextView>(R.id.userOrderName).text = user.getName()
                Glide.with(this).load(user.getAvatar()).fitCenter()
                    .into(findViewById(R.id.userOrderImage))
            }
            findViewById<TextView>(R.id.orderDate).text =
                LocalDateTime.parse(it.getOrderDate().toString(), DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            findViewById<TextView>(R.id.orderAddress).text = it.getShippingAddress()
            findViewById<TextView>(R.id.orderTotal).text = "Tổng tiền: " + it.getTotal().toString()
            if (it.getStatus() == 0) {
                findViewById<Button>(R.id.orderDetail_StatusBtn).text = "Đang giao"
            } else {
                findViewById<Button>(R.id.orderDetail_StatusBtn).text = "Đã giao"
            }
        }
        val orderProductRv = findViewById<RecyclerView>(R.id.orderProductRv)
        orderProductRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        orderProductProvider.getOrderProductByOrderId(orderId.toInt()).observe(this) {
            orderProductRv.adapter = OrderProductAdapter(this, it)
        }
    }
}