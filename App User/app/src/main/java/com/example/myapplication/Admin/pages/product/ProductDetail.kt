package com.example.myapplication.Admin.pages.product

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.modals.Product
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ProductDetail : AppCompatActivity() {
    private lateinit var product: Product

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        val intent = intent

        val productId = intent.getStringExtra("product_id")!!.toInt()
        val productViewModel = ViewModelProvider(this)[ProductController::class.java]
        findViewById<Button>(R.id.detailProduct_editBtn).setOnClickListener {
            val intent = Intent(this, EditProduct::class.java)
            intent.putExtra("product_id", productId.toString())
            startActivity(intent)
        }

        findViewById<Button>(R.id.detailProduct_cancelBtn).setOnClickListener {
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.detailProduct_disableBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setMessage("Bạn có muốn thay đổi trạng thái sản phẩm này không?")

            builder.setCancelable(true)

            builder.setNegativeButton(
                "Có"
            ) { dialog, id ->
                if (product.getIsDisable() == 0) {
                    productViewModel.disableProduct(productId).observe(this) {

                    }
                } else {
                    productViewModel.enableProduct(productId).observe(this) {

                    }
                }

                val intent = Intent(this, Products::class.java)
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

        findViewById<Button>(R.id.detailProduct_statusBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setMessage("Bạn có muốn thay đổi trạng thái sản phẩm này không?")

            builder.setCancelable(true)

            builder.setNegativeButton(
                "Có"
            ) { dialog, id ->
                if (product.getStatus() == 0) {
                    productViewModel.availableProduct(productId).observe(this) {

                    }
                } else {
                    productViewModel.unavailableProduct(productId).observe(this) {

                    }
                }

                val intent = Intent(this, Products::class.java)
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

        productViewModel.getProduct(productId).observe(this) {
            product = it
            findViewById<TextView>(R.id.detailProductName).text = it.getName()
            findViewById<TextView>(R.id.detailProductSize).text = it.getSize()
            findViewById<TextView>(R.id.priceSTv).text =
                "Giá S: " + it.getPriceS().toString() + "Đ"
            findViewById<TextView>(R.id.priceLTv).text =
                "Giá L: " + it.getPriceL().toString() + "Đ"
            findViewById<TextView>(R.id.priceMTv).text =
                "Giá M: " + it.getPriceM().toString() + "Đ"
            findViewById<TextView>(R.id.detailProductDesc).text = it.getDescription()
            it.getCategoryId()?.let { it1 ->
                ViewModelProvider(this)[CategoryController::class.java].getCategory(it1)
                    .observe(this) {
                        findViewById<TextView>(R.id.detailProductCategory).text = it.getName()
                    }
            }
            findViewById<TextView>(R.id.detailProductReleaseDate).text =
                LocalDateTime.parse(it.getReleaseDate().toString(), DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            findViewById<TextView>(R.id.detailProductUpdateDate).text =
                LocalDateTime.parse(it.getUpdateDate().toString(), DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            Glide.with(this).load(it.getImage()).fitCenter()
                .into(findViewById(R.id.detailProductAvatar))
            findViewById<TextView>(R.id.detailProductSales).text = it.getSales().toString()

            val isDisable = findViewById<Button>(R.id.detailProduct_disableBtn)
            if (it.getIsDisable() == 0) {
                isDisable.text = "Khóa"
            } else {
                isDisable.text = "Mở"
            }
            val status = findViewById<Button>(R.id.detailProduct_statusBtn)
            if (it.getStatus() == 0) {
                status.text = "Hết hàng"
            } else {
                status.text = "Còn hàng"
            }
        }
    }
}