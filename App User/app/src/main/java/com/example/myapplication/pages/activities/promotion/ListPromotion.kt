package com.example.myapplication.pages.activities.promotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewmodels.promotion.PromotionViewModel
import com.example.myapplication.modals.Promotion
//import com.example.myapplication.pages.activities.promotion.Promotions




class ListPromotion : AppCompatActivity() {
    lateinit var promotionRecycleview: RecyclerView
    lateinit var promotionViewModel: PromotionViewModel
    lateinit var promotionAdapterList: PromotionAdapterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion)
        promotionRecycleview = findViewById(R.id.recycleview_promotion)
        promotionRecycleview.setHasFixedSize(true)
        promotionRecycleview.layoutManager = LinearLayoutManager(this)

        promotionAdapterList = PromotionAdapterList(arrayListOf())
        promotionRecycleview.adapter = promotionAdapterList

        promotionViewModel = ViewModelProvider(this)[PromotionViewModel::class.java]
        promotionViewModel.getPromotions()
        promotionViewModel.promotions.observe(this, Observer {
            it?.let { resources ->
                if (resources.isEmpty()) {
                    Toast.makeText(this, "Have No Promotion!", Toast.LENGTH_SHORT).show()
                } else {
                    promotionAdapterList.promotionList = resources
                    promotionAdapterList.notifyDataSetChanged()
                }
            }
        })

        promotionAdapterList.onItemClick = { promotion->
            val intent = Intent(this, DetailPromotion::class.java)
            intent.putExtra("promotion", promotion as Parcelable)
            startActivity(intent)
        }

    }
}


