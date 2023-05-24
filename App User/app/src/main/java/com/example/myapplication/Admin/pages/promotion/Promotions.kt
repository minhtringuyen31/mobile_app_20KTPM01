package com.example.myapplication.Admin.pages.promotion

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.PromotionController
import com.example.myapplication.Admin.modals.Promotion
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Promotions : AppCompatActivity() {
    private lateinit var promotionList: List<Promotion>
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        println("new intent promotion")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        println("new intent oncreaete promotion")
        val rvPromotion = findViewById<RecyclerView>(R.id.promotionRV)
        val searchPromotion = findViewById<AutoCompleteTextView>(R.id.searchPromotion)
        findViewById<Button>(R.id.addPromotion).setOnClickListener {
            val intent = Intent(this, AddPromotion::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<Button>(R.id.backPromotionBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
        rvPromotion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val promotionViewAdapter = ViewModelProvider(this)[PromotionController::class.java]
        promotionViewAdapter.getAllPromotion().observe(this) {
            val promotions = it
            promotionList = it
            val adapter = PromotionAdapter(this, promotions)
            rvPromotion.adapter = adapter
            val adapterACTV = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_single_choice,
                promotionList
            )
            searchPromotion.setAdapter(adapterACTV)
        }
        searchPromotion.addTextChangedListener {
            val promotions = promotionList.filter { promotion ->
                promotion.getName()!!.contains(it.toString(), true)
            }
            val adapter = PromotionAdapter(this, promotions)
            rvPromotion.adapter = adapter
        }
    }
}