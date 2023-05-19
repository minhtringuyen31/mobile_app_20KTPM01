package com.example.myapplication.Admin.pages.statistics

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.controllers.*
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R
import com.example.myapplication.utils.Utils

class Statistics : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val productViewProvider = ViewModelProvider(this)[ProductController::class.java]
        val orderViewProvider = ViewModelProvider(this)[OrderController::class.java]
        val promotionViewProvider = ViewModelProvider(this)[PromotionController::class.java]
        val ratingViewProvider = ViewModelProvider(this)[RatingController::class.java]
        val userViewProvider = ViewModelProvider(this)[UserController::class.java]

        orderViewProvider.countOrder().observe(this) {
            println(it)
            findViewById<TextView>(R.id.orderAmount).text = "${it.getCount()} orders"
        }
        orderViewProvider.totalOrder().observe(this) {
            findViewById<TextView>(R.id.orderTotalSales).text = "${it.getTotal()
                ?.let { it1 -> Utils.formatCurrency(it1) }} VND"
        }
        productViewProvider.countProduct().observe(this) {
            findViewById<TextView>(R.id.productAmount).text = it.getCount().toString()
        }
        promotionViewProvider.countPromotion().observe(this) {
            findViewById<TextView>(R.id.promotionAmount).text = it.getCount().toString()
        }
        ratingViewProvider.countRating().observe(this) {
            findViewById<TextView>(R.id.ratingAmount).text = it.getCount().toString()
        }
        userViewProvider.countUser().observe(this) {
            findViewById<TextView>(R.id.userAmount).text = it.getCount().toString()
        }
        findViewById<Button>(R.id.backStatisticsBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}