package com.example.appadmin.pages.order

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
        orderProvider.getOrder(orderId!!.toInt()).observe(this) {
            userProvider.getUser(it.getUserId()!!).observe(this) { user ->
                findViewById<TextView>(R.id.userOrderName).text = user.getName()
                Glide.with(this).load(user.getAvatar()).fitCenter()
                    .into(findViewById(R.id.userOrderImage))
            }
            findViewById<TextView>(R.id.orderDate).text =
                LocalDateTime.parse(it.getOrderDate().toString(), DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            findViewById<TextView>(R.id.orderAddress).text = it.getShippingAddress()
            findViewById<TextView>(R.id.totalOrder).text = it.getTotal().toString()
            findViewById<TextView>(R.id.orderStatus).text = it.getStatus().toString()

        }
        val orderProductRv = findViewById<RecyclerView>(R.id.orderProductRv)
        orderProductRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        orderProductProvider.getOrderProductByOrderId(orderId.toInt()).observe(this) {
            orderProductRv.adapter = OrderProductAdapter(this, it)
        }
    }
}