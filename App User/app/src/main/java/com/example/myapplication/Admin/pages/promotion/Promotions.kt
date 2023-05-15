package com.example.myapplication.Admin.pages.promotion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.PromotionController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Promotions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)

        val rvPromotion = findViewById<RecyclerView>(R.id.promotionRV)
        findViewById<Button>(R.id.addPromotion).setOnClickListener {
            val intent = Intent(this, AddPromotion::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.backPromotionBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvPromotion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val promotionViewAdapter = ViewModelProvider(this)[PromotionController::class.java]
        promotionViewAdapter.getAllPromotion().observe(this) {
            val promotions = it

            val adapter = PromotionAdapter(this, promotions)
            rvPromotion.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}