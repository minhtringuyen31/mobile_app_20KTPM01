package com.example.appadmin.pages.order

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val acceptBtn = findViewById<Button>(R.id.orderDetail_AcceptBtn)
        val denyBtn = findViewById<Button>(R.id.orderDetail_DenyBtn)
        val statusBtn = findViewById<Button>(R.id.orderDetail_StatusBtn)


        val orderProvider = ViewModelProvider(this)[OrderController::class.java]
        val orderProductProvider = ViewModelProvider(this)[OrderProductController::class.java]
        val userProvider = ViewModelProvider(this)[UserController::class.java]
        findViewById<Button>(R.id.orderCancelBtn).setOnClickListener {
            val intent = Intent(this, Orders::class.java)
            startActivity(intent)
        }
        acceptBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Bạn có muốn xác nhận đơn hàng không?")
            builder.setCancelable(true)
            builder.setNegativeButton("Có") { dialog, which ->
                orderProvider.changeAcceptStatus(orderId!!.toInt()).observe(this) {}
                val intent = Intent(this, Orders::class.java)
                startActivity(intent)
                dialog.cancel()
            }
            builder.setPositiveButton("Không") { dialog, which ->
                dialog.cancel()
            }
            val dialog = builder.create()
            dialog.show()
        }
        denyBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Bạn có muốn từ chối đơn hàng không?")
            builder.setCancelable(true)
            builder.setNegativeButton("Có") { dialog, which ->
                orderProvider.changeDenyStatus(orderId!!.toInt()).observe(this) {}
                val intent = Intent(this, Orders::class.java)
                startActivity(intent)
                dialog.cancel()
            }
            builder.setPositiveButton("Không") { dialog, which ->
                dialog.cancel()
            }
            val dialog = builder.create()
            dialog.show()
        }
        statusBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Đơn hàng đã giao thành công?")
            builder.setCancelable(true)
            builder.setNegativeButton("Đúng") { dialog, which ->
                orderProvider.changeDeliveredStatus(orderId!!.toInt()).observe(this) {}
                val intent = Intent(this, Orders::class.java)
                startActivity(intent)
                dialog.cancel()
            }
            builder.setPositiveButton("Không") { dialog, which ->
                dialog.cancel()
            }
            val dialog = builder.create()
            dialog.show()
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
            findViewById<TextView>(R.id.orderStatus).text = when (it.getStatus()) {
                0 -> "Đang xử lý"
                1 -> "Đang giao"
                2 -> "Đã giao"
                else -> "Đã hủy"
            }
            if (orderStatus == 0) {
                acceptBtn.visibility = View.VISIBLE
                denyBtn.visibility = View.VISIBLE
                statusBtn.visibility = View.GONE
            } else if (orderStatus == 1) {
                acceptBtn.visibility = View.GONE
                denyBtn.visibility = View.GONE
                statusBtn.visibility = View.VISIBLE
            } else {
                acceptBtn.visibility = View.GONE
                denyBtn.visibility = View.GONE
                statusBtn.visibility = View.GONE
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