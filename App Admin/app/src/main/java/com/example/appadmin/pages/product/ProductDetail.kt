package com.example.appadmin.pages.product

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.controllers.ProductController
import com.example.appadmin.modals.Product

class ProductDetail : AppCompatActivity() {
    private lateinit var product: Product

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
            findViewById<TextView>(R.id.priceSTv).text =
                "Size S: " + it.getPriceS().toString() + "VND"
            findViewById<TextView>(R.id.priceLTv).text =
                "Size L: " + it.getPriceL().toString() + "VND"
            findViewById<TextView>(R.id.priceMTv).text =
                "Size M: " + it.getPriceM().toString() + "VND"
            findViewById<TextView>(R.id.detailProductDesc).text = it.getDescription()
            it.getCategoryId()?.let { it1 ->
                ViewModelProvider(this)[CategoryController::class.java].getCategory(it1)
                    .observe(this) {
                        findViewById<TextView>(R.id.detailProductCategory).text = it.getName()
                    }
            }
            findViewById<TextView>(R.id.detailProductReleaseDate).text =
                it.getReleaseDate().toString().split("T")[0]
            findViewById<TextView>(R.id.detailProductUpdateDate).text =
                it.getUpdateDate().toString().split("T")[0]
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