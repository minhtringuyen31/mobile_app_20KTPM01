package com.example.myapplication.pages.activities.promotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.pages.activities.store.IntroductionStore
import com.example.myapplication.viewmodels.promotion.PromotionViewModel


class DetailPromotion : AppCompatActivity() {
    private lateinit var btn_back: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_promotion_detail)
        val promotion = intent.getParcelableExtra<PromotionData>("promotion")
        if (promotion != null) {
            val img_promotionDetail: ImageView = findViewById(R.id.img_promotionDetail)
            val name_promotionDetail: TextView = findViewById(R.id.name_promotionDetail)
            val discount_promotionDetail: TextView = findViewById(R.id.discount_promotionDetail)
            val start_promotionDetail: TextView = findViewById(R.id.start_promotionDetail)
            val end_promotionDetail: TextView = findViewById(R.id.end_promotionDetail)
            val desc_promotionDetail: TextView = findViewById(R.id.desc_promotionDetail)
            val button_applyPromotion: Button = findViewById(R.id.button_applyPromotion)


            Glide.with(this)
                .load(promotion.getImage())
                .into(img_promotionDetail)
            name_promotionDetail.text = promotion.getName()
            discount_promotionDetail.text = promotion.getDiscount().toString() +"%"
            start_promotionDetail.text = promotion.getBeginDay().take(10)
            end_promotionDetail.text = promotion.getEndDay().take(10)
            desc_promotionDetail.text = promotion.getDescription()

            btn_back=findViewById(R.id.back_btn3)
            btn_back.setOnClickListener {
                val intent = Intent(
                    this,
                    // Signup::class.java
                    ListPromotion::class.java
                )
                startActivity(intent)
            }



            button_applyPromotion.setOnClickListener {
                Toast.makeText(this, "Discount is  "+promotion.getDiscount().toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}