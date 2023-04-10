package com.example.myapplication.pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Product
import java.util.logging.Logger

class ProductDetail : AppCompatActivity() {
    private var productDetailImage : ImageView? = null
    private var productDetailName : TextView? = null
    private var productDetailPrice  :TextView? = null
    private var productDetailDescription : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initUI()
        val intent = intent
        var productDetailName  =  intent.getStringExtra("name").toString()
        var productDetailPrice =  intent.getStringExtra("price").toString()
        var productDetailDescription = intent.getStringExtra("description").toString()
        var productDetailImage = intent.getStringExtra("image").toString()
        setProductDetail(productDetailName, productDetailPrice, productDetailDescription, productDetailImage)
    }
    private fun initUI(){
        productDetailImage = findViewById(R.id.productDetailImageIV)
        productDetailName = findViewById(R.id.productDetailNameTV)
        productDetailPrice = findViewById(R.id.productDetailPriceTV)
        productDetailDescription = findViewById((R.id.productDetailDescriptionTV))
    }
    private fun setProductDetail(productName: String, productPrice: String, productDescription: String, productImage: String){
        productDetailName!!.text = productName
        productDetailPrice!!.text = productPrice
        productDetailDescription!!.text = productDescription
        Glide.with(this)
            .load(productImage)
            .into(productDetailImage!!)
    }
}

