package com.example.appadmin.pages.promotion

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.PromotionController
import com.example.appadmin.modals.Promotion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PromotionDetail : AppCompatActivity() {
    private lateinit var promotion: Promotion

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_detail)

        val intent = intent

        val promotionId = intent.getStringExtra("promotionId")!!.toInt()
        val promotionViewProvider = ViewModelProvider(this)[PromotionController::class.java]

        findViewById<Button>(R.id.detailPromotion_editBtn).setOnClickListener {
            val intent = Intent(this, EditPromotion::class.java)
            intent.putExtra("promotionId", promotionId.toString())
            startActivity(intent)
        }

        findViewById<Button>(R.id.detailPromotion_cancelBtn).setOnClickListener {
            val intent = Intent(this, Promotions::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.detailPromotion_deleteBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setMessage("Bạn có muốn xóa khuyến mãi này không?")

            builder.setCancelable(true)

            builder.setNegativeButton(
                "Có"
            ) { dialog, id ->
                promotionViewProvider.deletePromotion(promotionId).observe(this) {

                }

                val intent = Intent(this, Promotions::class.java)
                startActivity(intent)

                dialog.cancel()
            }

            builder.setPositiveButton(
                "Không"
            ) { dialog, id ->
                dialog.cancel()
            }

            val alert: AlertDialog = builder.create()
            alert.show()
        }

        findViewById<Button>(R.id.detailPromotion_disableBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setMessage("Bạn có muốn thay đổi trạng thái khuyến mãi này không?")

            builder.setCancelable(true)

            builder.setNegativeButton(
                "Có"
            ) { dialog, id ->
                if (promotion.getIsDisable() == 0) {
                    promotionViewProvider.disablePromotion(promotionId).observe(this) {

                    }
                } else {
                    promotionViewProvider.enablePromotion(promotionId).observe(this) {

                    }
                }

                val intent = Intent(this, Promotions::class.java)
                startActivity(intent)

                dialog.cancel()
            }

            builder.setPositiveButton(
                "Không"
            ) { dialog, id ->
                dialog.cancel()
            }

            val alert: AlertDialog = builder.create()
            alert.show()
        }

        promotionViewProvider.getPromotion(promotionId).observe(this) {
            promotion = it
            findViewById<Button>(R.id.detailPromotion_disableBtn).text =
                if (promotion.getIsDisable() == 0) "Khóa" else "Mở"
            Glide.with(this).load(promotion.getImage())
                .into(findViewById(R.id.detailPromotionImage))
            findViewById<TextView>(R.id.detailPromotionName).text = promotion.getName()
            findViewById<TextView>(R.id.detailPromotionDesc).text =
                promotion.getDescription()
            findViewById<TextView>(R.id.detailPromotionDiscount).text =
                "Discount: " + promotion.getDiscount().toString() + "%"
            findViewById<TextView>(R.id.detailPromotionStartDate).text =
                LocalDateTime.parse(
                    promotion.getStartDate().toString(),
                    DateTimeFormatter.ISO_DATE_TIME
                )
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            findViewById<TextView>(R.id.detailPromotionEndDate).text =
                LocalDateTime.parse(
                    promotion.getEndDate().toString(),
                    DateTimeFormatter.ISO_DATE_TIME
                )
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            findViewById<TextView>(R.id.detailPromotionCode).text =
                "Code: " + promotion.getCode()
            findViewById<TextView>(R.id.detailPromotionQuantity).text =
                promotion.getQuantity().toString() + " mã"
        }
    }
}