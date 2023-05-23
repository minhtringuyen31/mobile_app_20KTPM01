package com.example.myapplication.Admin.pages.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.modals.Product
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R


class Products : AppCompatActivity() {
    private lateinit var productList: List<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val rvProduct = findViewById<RecyclerView>(R.id.productRV)
        val searchProduct = findViewById<AutoCompleteTextView>(R.id.searchProduct)
        val productSpinner = findViewById<Spinner>(R.id.productSpinner)
        findViewById<Button>(R.id.addProduct).setOnClickListener {
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.backProductBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ViewModelProvider(this)[CategoryController::class.java].getAllCategory().observe(this) {
            var categories = it
            categories = categories.reversed()

            val categoryListName = mutableListOf<String>()
            categoryListName.add("All")
            for (category in categories) {
                println(category.getName())
                category.getName()?.let { it1 -> categoryListName.add(it1) }
            }
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoryListName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                productSpinner.adapter = adapter
            }
        }
        val productViewAdapter = ViewModelProvider(this)[ProductController::class.java]
        productViewAdapter.getAllProduct().observe(this) {
            val products = it
            productList = it

            val adapter = ProductAdapter(this, products)
            rvProduct.adapter = adapter
            val adapterACTV = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                productList
            )
            searchProduct.setAdapter(adapterACTV)
        }
        searchProduct.addTextChangedListener {
            val adapter = ProductAdapter(this, productList.filter { product ->
                product.getName()!!.contains(searchProduct.text.toString(), true)
            })
            rvProduct.adapter = adapter
        }
        productSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (position == 0) {
                    val adapter = ProductAdapter(this@Products, productList)
                    rvProduct.adapter = adapter
                } else {
                    val adapter = ProductAdapter(this@Products, productList.filter { product ->
                        product.getCategoryId()!! == position
                    })
                    rvProduct.adapter = adapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}