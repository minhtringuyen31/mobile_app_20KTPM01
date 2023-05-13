package com.example.myapplication.pages.activities.promotion

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.viewmodels.promotion.PromotionViewModel
import com.example.myapplication.modals.Promotion
import com.example.myapplication.pages.fragments.Homepage
import com.example.myapplication.pages.fragments.Others
import com.example.myapplication.viewmodels.order.CheckoutViewModel

//import com.example.myapplication.pages.activities.promotion.Promotions




class ListPromotion : AppCompatActivity() {
    lateinit var promotionRecycleview: RecyclerView

    lateinit var promotionViewModel: PromotionViewModel
    lateinit var promotionAdapterList: PromotionAdapterList
    lateinit var back_btn: AppCompatImageView
    private val REQUEST_CODE_DETAIL_PROMOTION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion)
        val forward = intent
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
            intent.putExtra("from", forward.getStringExtra("from"))
            startActivityForResult(intent, REQUEST_CODE_DETAIL_PROMOTION)
        }
       back_btn=findViewById(R.id.back_btn2)
        back_btn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("FragmentToLoad", "Others")
            startActivity(intent)
        }


    }
}


