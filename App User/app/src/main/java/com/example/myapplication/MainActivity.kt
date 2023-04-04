package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.modals.Category
import com.example.myapplication.modals.Product
import com.example.myapplication.pages.CategoryListAdapter
import com.example.myapplication.pages.ProductListAdapter

class MainActivity : AppCompatActivity() {
    private val listProduct = arrayListOf(
        Product("Product 1", "35,000 đ",R.drawable.product1, "This is product 1"),
        Product("Product 2", "35,000 đ",R.drawable.product2, "This is product 2"),
        Product("Product 3", "35,000 đ",R.drawable.product3, "This is product 3"),
        Product("Product 4", "35,000 đ", R.drawable.product4, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product5, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product6, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product7, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product8, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product9, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product10, "This is product 4"),
        Product("Product 4", "35,000 đ", R.drawable.product11, "This is product 4")
    )
    private val listCategory = arrayListOf(
        Category("CÀ PHÊ", "LINK"),
        Category("BÁNH", "LINK"),
        Category("TRÀ", "LINK"),
        Category("PHINDI", "LINK"),
        Category("ĐÁ XAY", "LINK"),
        Category("THỨC UỐNG KHÁC","LINK")
    )
    private var productRecyclerView: RecyclerView? = null
    private var categoryRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_product_list)

        categoryRecyclerView = findViewById(R.id.listCategoryRV)
        var adapter2 = CategoryListAdapter(listCategory)
        categoryRecyclerView!!.adapter = adapter2
        categoryRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false
        )

        productRecyclerView = findViewById(R.id.listProductRV)
        var adapter1 = ProductListAdapter(listProduct)
        productRecyclerView!!.adapter = adapter1
        productRecyclerView!!.layoutManager = GridLayoutManager(this,2)
//        productRecyclerView!!.addItemDecoration(
//            DividerItemDecoration(
//                this,
//                DividerItemDecoration.VERTICAL
//
//            )
//        )
    }
}